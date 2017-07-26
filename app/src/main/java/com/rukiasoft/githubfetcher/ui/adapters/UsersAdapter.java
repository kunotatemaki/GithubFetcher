package com.rukiasoft.githubfetcher.ui.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rukiasoft.githubfetcher.BR;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.databinding.ActivityListBinding;
import com.rukiasoft.githubfetcher.databinding.UserBasicItemBinding;
import com.rukiasoft.githubfetcher.model.UserBasic;

import static com.bumptech.glide.request.RequestOptions.circleCropTransform;


import java.util.List;

/**
 * Created by Roll on 21/7/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.BindingHolder>
implements View.OnClickListener{
    private List<UserBasic> mUsers;

    private OnCardClickListener onCardClickListener;

    @Override
    public void onClick(final View view) {
        // Give some time to the ripple to finish the effect
        if (onCardClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onCardClickListener.onCardClick(view, (UserBasic) view.getTag());
                }
            }, 200);
        }

    }

    public void setOnCardClickListener(OnCardClickListener onCardClickListener) {
        this.onCardClickListener = onCardClickListener;
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private UserBasicItemBinding binding;

        BindingHolder(UserBasicItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserBasic user){
            binding.setVariable(BR.user, user);
            binding.cardView.setTag(user);
            Glide.with(binding.getRoot().getContext())
                    .load(user.getAvatarUrl())
                    .apply(circleCropTransform()).into(binding.profilePic);
            binding.executePendingBindings();
        }

    }

    public UsersAdapter(List<UserBasic> recyclerUsers) {
        this.mUsers = recyclerUsers;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());
        UserBasicItemBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.user_basic_item, parent, false);
        //evento de click
        binding.cardView.setOnClickListener(this);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final UserBasic user = mUsers.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public interface OnCardClickListener{
        void onCardClick(View view, UserBasic user);
    }
}


