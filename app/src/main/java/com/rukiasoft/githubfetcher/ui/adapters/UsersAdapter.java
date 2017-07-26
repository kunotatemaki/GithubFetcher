package com.rukiasoft.githubfetcher.ui.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.databinding.UserBasicItemBinding;
import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.ui.viewholders.UserBasicViewHolder;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;

import java.util.List;

import static com.bumptech.glide.request.RequestOptions.circleCropTransform;

/**
 * Created by Roll on 21/7/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UserBasicViewHolder>{
    private List<UserBasic> mUsers;

    private ListActivityContracts.ProvidedPresenterOps presenter;


    public UsersAdapter(List<UserBasic> recyclerUsers, ListActivityContracts.ProvidedPresenterOps presenter) {
        this.mUsers = recyclerUsers;
        this.presenter = presenter;
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext()).load(url)
                .apply(circleCropTransform()).into(view);
    }

    @Override
    public UserBasicViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());
        UserBasicItemBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.user_basic_item, parent, false);
        return new UserBasicViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserBasicViewHolder holder, int position) {
        final UserBasic user = mUsers.get(position);
        holder.bind(user, presenter);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}


