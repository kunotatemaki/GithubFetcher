package com.rukiasoft.githubfetcher.ui.viewholders;

import android.support.v7.widget.RecyclerView;

import com.rukiasoft.githubfetcher.BR;
import com.rukiasoft.githubfetcher.databinding.UserBasicItemBinding;
import com.rukiasoft.githubfetcher.model.UserBasic;
import com.rukiasoft.githubfetcher.ui.presenters.interfaces.ListActivityContracts;

/**
 * Created by Roll on 26/7/17.
 */

public class UserBasicViewHolder  extends RecyclerView.ViewHolder {
    private UserBasicItemBinding binding;

    public UserBasicViewHolder(UserBasicItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(UserBasic user, ListActivityContracts.ProvidedPresenterOps presenter){
        binding.setVariable(BR.user, user);
        binding.setVariable(BR.presenter, presenter);
        binding.cardView.setTag(user);
        binding.executePendingBindings();
    }
}
