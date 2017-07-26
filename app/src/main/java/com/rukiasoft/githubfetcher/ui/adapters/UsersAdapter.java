package com.rukiasoft.githubfetcher.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.rukiasoft.githubfetcher.BR;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.databinding.UserBasicItemBinding;
import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;

import java.util.List;

import static com.bumptech.glide.request.RequestOptions.circleCropTransform;

/**
 * Created by Roll on 21/7/17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.BindingHolder>{
    private List<UserBasic> mUsers;

    private ListActivityContracts.ProvidedPresenterOps presenter;

    static class BindingHolder extends RecyclerView.ViewHolder {
        private UserBasicItemBinding binding;

        BindingHolder(UserBasicItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(UserBasic user, ListActivityContracts.ProvidedPresenterOps presenter){
            binding.setVariable(BR.user, user);
            binding.setVariable(BR.presenter, presenter);
            binding.cardView.setTag(user);
            Glide.with(binding.getRoot().getContext())
                    .load(user.getAvatarUrl())
                    .apply(circleCropTransform()).into(binding.profilePic);
            binding.executePendingBindings();
        }
    }

    public UsersAdapter(List<UserBasic> recyclerUsers, ListActivityContracts.ProvidedPresenterOps presenter) {
        this.mUsers = recyclerUsers;
        this.presenter = presenter;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        LayoutInflater inflater =
                LayoutInflater.from(parent.getContext());
        UserBasicItemBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.user_basic_item, parent, false);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final UserBasic user = mUsers.get(position);
        holder.bind(user, presenter);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}


