package com.pf.dllo.food.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pf.dllo.food.R;
import com.pf.dllo.food.bean.LibFraBean;
import com.pf.dllo.food.fragment.BaseFragment;
import com.pf.dllo.food.tools.ITextChanged;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/11/25.
 */
public class LibraryGvAdapter extends BaseAdapter{

    private List<LibFraBean.GroupBean.CategoriesBean>datas;

    private Context mContext;



    public void setDatas(List<LibFraBean.GroupBean.CategoriesBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    public LibraryGvAdapter(Context context) {
        mContext = context;
    }



    //    public void setDatas(Map<String, List<LibFraBean.GroupBean.CategoriesBean>> datas) {
//        this.datas = datas;
//        notifyDataSetChanged();
//    }


    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int i) {
        return  datas == null ? null : datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LibVH libVH = null;
        BraVH braVH = null;
        if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_gv_library, viewGroup, false);
                libVH = new LibVH(view);
                view.setTag(libVH);

        }else {
//            if (kinds.get(i).getKind() == "group") {
                libVH = (LibVH) view.getTag();
//            }

        }

//        if (kinds.get(i).getKind() == "group") {



            libVH.tvLib.setText(datas.get(i).getName());
            Glide.with(mContext).load(datas.get(i).getImage_url()).into(libVH.ivLib);

//        }
//    else {
//            braVH.tvBrand.setText(datas.get(i).getName());
//            Glide.with(mContext).load(datas.get(i).getImage_url()).into(braVH.ivBrand);
//        }
        return view;
    }
    class LibVH{
        TextView tvLib;
       ImageView ivLib;

        public LibVH(View view) {
            tvLib = (TextView) view.findViewById(R.id.tv_item_library);
            ivLib = (ImageView) view.findViewById(R.id.iv_item_library);
        }
    }
    class BraVH{
        TextView tvBrand;
        ImageView ivBrand;

        public BraVH(View view) {
            tvBrand = (TextView) view.findViewById(R.id.tv_item_brand);
            ivBrand = (ImageView) view.findViewById(R.id.iv_item_brand);
        }
    }
}
