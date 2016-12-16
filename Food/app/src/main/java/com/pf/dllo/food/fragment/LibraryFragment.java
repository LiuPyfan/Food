package com.pf.dllo.food.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.android.volley.VolleyError;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.lib.LibSearchAty;
import com.pf.dllo.food.activity.lib.LibBrandAty;
import com.pf.dllo.food.activity.lib.LibKind2Aty;
import com.pf.dllo.food.adapter.lib.LibraryGvAdapter;
import com.pf.dllo.food.bean.LibFraBean;
import com.pf.dllo.food.tools.MyScrollView;
import com.pf.dllo.food.tools.net.NetHelper;
import com.pf.dllo.food.tools.net.NetListener;
import com.pf.dllo.food.values.NetValues;

import java.util.List;

/**
 * 食物百科一级界面
 */
public class LibraryFragment extends BaseFragment {

    private MyScrollView mScrollView;


    private GridView mGvLib, mGvBrand, mGvChain;
    private Button mBtnSearch;
    private List<LibFraBean.GroupBean.CategoriesBean> mDatas, brandDatas, chainDatas;


    @Override
    protected int setLayout() {
        return R.layout.fragment_library;
    }

    @Override
    protected void initView(View view) {
        mScrollView = bindView(R.id.scroll_root);
        mGvLib = bindView(R.id.gv_library_category);
        mGvBrand = bindView(R.id.gv_library_brand);
        mGvChain = bindView(R.id.gv_library_chain);
        mBtnSearch = bindView(R.id.btn_library_search);// 搜索



    }

    @Override
    protected void initData() {

        mScrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldx, int oldy) {
            }
        });



        // 网络请求
        NetHelper.MyRequest(NetValues.LIB_GV, LibFraBean.class, new NetListener<LibFraBean>() {
                    @Override
                    public void successListener(LibFraBean response) {

                        for (int i = 0; i < 3; i++) {
                            if (i == 0) {

                                mDatas = response.getGroup().get(0).getCategories();
                                LibraryGvAdapter libraryAdapter = new LibraryGvAdapter(mContext);
                                libraryAdapter.setDatas(mDatas);
                                mGvLib.setAdapter(libraryAdapter);
                                GvLibClick(response);

                            } else if (i == 1) {


                                brandDatas = response.getGroup().get(1).getCategories();
                                LibraryGvAdapter brandAdapter = new LibraryGvAdapter(mContext);
                                brandAdapter.setDatas(brandDatas);
                                mGvBrand.setAdapter(brandAdapter);

                                GvBrandClick(response);

                            } else if (i == 2) {

                                chainDatas = response.getGroup().get(2).getCategories();
                                LibraryGvAdapter chainAdapter = new LibraryGvAdapter(mContext);
                                chainAdapter.setDatas(chainDatas);
                                mGvChain.setAdapter(chainAdapter);

                                GvChainClick(response);

                            }
                        }

                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                }

        );

        mBtnSearch.setOnClickListener(new View.OnClickListener()

                                      {
                                          @Override
                                          public void onClick(View view) {
                                              Intent intent = new Intent().setClass(mContext, LibSearchAty.class);
                                              startActivity(intent);
                                          }
                                      }

        );

    }

    private void GvLibClick(final LibFraBean response) {
        mGvLib.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                      {
                                          @Override
                                          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                              // 第二个pop
                                              String gvLibUrl = NetValues.GV_LIB_SORT_HEAD + "group" + NetValues.GV_LIB_SORT_MID + mDatas.get(i).getId() + NetValues.GV_LIB_SORT_TAIL;
//                                              Intent intent = new Intent(mContext, LibKindAty.class);
                                              Intent intent = new Intent(mContext, LibKind2Aty.class);
                                              intent.putExtra("libUrl", gvLibUrl);
                                              // 上面的pop
                                              int id = mDatas.get(i).getId();
                                              int count = mDatas.get(i).getSub_category_count();

                                              String kind = response.getGroup().get(0).getKind();
                                              intent.putExtra("kind", kind);
                                              intent.putExtra("pos", i);
                                              intent.putExtra("id", id);
                                              intent.putExtra("count", count);

                                              startActivity(intent);

                                          }
                                      }


        );
    }

    private void GvBrandClick(final LibFraBean response) {
        mGvBrand.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                        {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                String gvBrandUrl = NetValues.GV_LIB_SORT_HEAD + "brand" + NetValues.GV_LIB_SORT_MID + brandDatas.get(i).getId() + NetValues.GV_LIB_SORT_TAIL;
                                                String kind = response.getGroup().get(1).getKind();
//                                                Intent intent = new Intent(mContext, LibKindAty.class);
                                                Intent intent = new Intent(mContext, LibBrandAty.class);
                                                intent.putExtra("libUrl", gvBrandUrl);
                                                // 上面的pop
                                                int id = brandDatas.get(i).getId();
                                                int count = brandDatas.get(i).getSub_category_count();
                                                intent.putExtra("kind", kind);
                                                intent.putExtra("pos", i);
                                                intent.putExtra("id", id);
                                                intent.putExtra("count", count);
                                                startActivity(intent);


                                            }
                                        }

        );
    }

    private void GvChainClick(final LibFraBean response) {
        mGvChain.setOnItemClickListener(new AdapterView.OnItemClickListener()

                                        {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                String gvChainUrl = NetValues.GV_LIB_SORT_HEAD + "restaurant" + NetValues.GV_LIB_SORT_MID + chainDatas.get(i).getId() + NetValues.GV_LIB_SORT_TAIL;

                                                Intent intent = new Intent(mContext, LibBrandAty.class);
                                                intent.putExtra("libUrl", gvChainUrl);
                                                // 上面的pop
                                                int id = chainDatas.get(i).getId();
                                                int count = chainDatas.get(i).getSub_category_count();
                                                String kind = response.getGroup().get(2).getKind();
                                                intent.putExtra("kind", kind);
                                                intent.putExtra("pos", i);
                                                intent.putExtra("id", id);
                                                intent.putExtra("count", count);
                                                startActivity(intent);


                                            }
                                        }
        );
    }


}
