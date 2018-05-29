package com.example.athis.myapplication.RecyclerInteract;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.athis.myapplication.Base.BaseActivity;
import com.example.athis.myapplication.R;
import com.example.athis.myapplication.adapters.BaseAdapter;
import com.example.athis.myapplication.adapters.BaseViewHolder;
import com.example.athis.myapplication.dataBean.SelectBean;
import com.example.athis.myapplication.utils.AssetUtil;
import com.example.athis.myapplication.utils.DoubleRecUtils;
import com.example.athis.myapplication.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DoubleRecActivity extends BaseActivity {

    @BindView(R.id.recylcer_left)
    RecyclerView recyclerLeft;
    @BindView(R.id.recycler_right)
    RecyclerView recyclerRight;

    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    private RecyclerView.LayoutManager managerR;

    private List<SelectBean> leftDataList = new ArrayList<>();
    private List<SelectBean.HobbiesBean> rightDataList = new ArrayList<>();
//    表示左右recycler index的对应关系 L -> R
    private List<Integer> reflect = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initRecycler();
    }

    public void initRecycler(){
        recyclerLeft.setLayoutManager(new MyLinearManager(this));
        leftAdapter = new LeftAdapter();
        rightAdapter = new RightAdapter();
        recyclerLeft.setAdapter(leftAdapter);
        leftAdapter.setListData(leftDataList);
        managerR = new GridLayoutManager(this,3);
        ((GridLayoutManager)managerR).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(DoubleRecUtils.isTitle(rightDataList.get(position)))
                    return 3;
                return 1;
            }
        });
        recyclerRight.setLayoutManager(managerR);
        rightAdapter.setListData(rightDataList);
        rightAdapter.setOnLongClickListener(new BaseAdapter.onLongClickListener<SelectBean.HobbiesBean>() {
            @Override
            public void onLongClick(BaseViewHolder<SelectBean.HobbiesBean> holder, SelectBean.HobbiesBean item, int index) {
                if(!DoubleRecUtils.isTitle(item)){
                    Toast.makeText(DoubleRecActivity.this, item.getLname(),Toast.LENGTH_LONG);
                }
            }
        });
        recyclerRight.setAdapter(rightAdapter);
        initLeftListener();
        initRightListener();
    }

    public void leftSelected(int index){
        recyclerLeft.scrollToPosition(index);
        leftAdapter.setIsSelected(index);
        leftAdapter.notifyDataSetChanged();
    }

    private boolean movable = false;
    private int mPositionL = 0;

    public void initLeftListener(){
        leftAdapter.setOnClickListener(new BaseAdapter.onClickListener<SelectBean>() {
            @Override
            public void onClick(BaseViewHolder<SelectBean> holder, SelectBean item, int index) {
                leftClickFlag = false;
                leftSelected(index);
                int first, last;
                managerR = recyclerRight.getLayoutManager();
                first = ((LinearLayoutManager)managerR).findFirstVisibleItemPosition();
                last = ((LinearLayoutManager)managerR).findLastVisibleItemPosition();
                mPositionL = reflect.get(index);
                Log.d("Position: ","First: "+first+", Last: "+last);
                if(mPositionL < first){
                    recyclerRight.smoothScrollToPosition(mPositionL);
                }else if(mPositionL >= first && mPositionL <= last){
                    View itemView = managerR.findViewByPosition(mPositionL);
                    int viewStart = managerR.getDecoratedTop(itemView);
                    int recStart = managerR.getPaddingTop();
                    recyclerRight.smoothScrollBy(0,viewStart - recStart);
                }else{
                    recyclerRight.scrollToPosition(mPositionL);
                    movable = true;
                }
            }
        });
    }

    private int curRight = 0;
//    右侧rec是否被手指滑动，防止左右重复相互调用
    private boolean leftClickFlag = true;
    public void initRightListener(){
        recyclerRight.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(movable){
                    movable = false;
                    managerR = recyclerView.getLayoutManager();
                    int i = mPositionL - ((LinearLayoutManager)managerR).findFirstVisibleItemPosition();
                    if(i >=0 && i <= managerR.getChildCount()){
                        View itemView = managerR.getChildAt(i);
                        int viewStart = managerR.getDecoratedTop(itemView);
                        int recStart = managerR.getPaddingTop();
                        recyclerView.smoothScrollBy(0,viewStart - recStart);
                    }
                }
                Log.d("leftClickFlag",leftClickFlag+"");
                int curPos = ((LinearLayoutManager)managerR).findFirstVisibleItemPosition();
                if(leftClickFlag){
                    if(DoubleRecUtils.isTitle(rightDataList.get(curPos))){
                        curRight = reflect.indexOf(curPos);
                        if(!leftAdapter.isSelected(curRight)){
                            leftSelected(curRight);
                        }
                        Log.d("Current Position >:","curPos: "+curPos+", curRight: "+curRight);
                    }else{
                        if(curPos < reflect.get(curRight) && (reflect.get(curRight) - curPos) <= 3){
                            if(!leftAdapter.isSelected(curRight - 1)){
                                leftSelected(curRight - 1);
                                Log.d("Current Position <:","curPos: "+curPos+", curRight: "+curRight);
                            }
                       }
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    leftClickFlag = true;
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int evX, evY;
//        int[] location = new int[2];
//        evX = (int) ev.getX();
//        evY = (int) ev.getY();
//        recyclerRight.getLocationOnScreen(location);
//        if(evX >= location[0] && evX <= location[0] + recyclerRight.getWidth()
//                && evY >= location[1] && evY <= location[1]+recyclerRight.getHeight())
//            leftClickFlag = true;
//        return super.dispatchTouchEvent(ev);
//    }

    public void initData(){
        Gson gson= new Gson();
        leftDataList = gson.fromJson(AssetUtil.getFileFromAsset(this),new TypeToken<List<SelectBean>>(){}.getType());
        List<SelectBean.HobbiesBean> temp = gson.fromJson(AssetUtil.getFileFromAsset(this),new TypeToken<List<SelectBean.HobbiesBean>>(){}.getType());
        for(int i = 0; i < temp.size(); i++){
            rightDataList.add(temp.get(i));
            rightDataList.addAll(leftDataList.get(i).getHobbies());
        }
        for(int i = 0; i < leftDataList.size();i++){
            for(int j = 0; j < rightDataList.size(); j++){
                if(TextUtils.equals(leftDataList.get(i).getLid(),rightDataList.get(j).getLid())){
                    reflect.add(i, j);
                }
            }
        }
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_double_rec;
    }
}
