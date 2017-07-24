package com.rukiasoft.githubfetcher.ui.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rukiasoft.githubfetcher.R;
import com.rukiasoft.githubfetcher.model.UserBasic;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import static com.bumptech.glide.request.RequestOptions.circleCropTransform;

/**
 * Created by Roll on 21/7/17.
 */
@Singleton
public class UsersAdapterOld extends RecyclerView.Adapter<UsersAdapterOld.BindingHolder>
implements View.OnClickListener{



        private final List<UserBasic> mItems;
        private OnCardClickListener onCardClickListener;
        private final Context mContext;

        public UsersAdapterOld(Context context, List<UserBasic> items) {
            this.mItems = new ArrayList<>(items);
            this.mContext = context;
        }

        public void setOnCardClickListener(OnCardClickListener onCardClickListener) {
            this.onCardClickListener = onCardClickListener;
        }

        public int getPositionOfItem(UserBasic item){
            return mItems.indexOf(item);
        }

        @Override
        public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_basic_item, parent, false);
            BindingHolder pictureViewHolder = new BindingHolder(v);
            pictureViewHolder.cardView.setOnClickListener(this);

            return pictureViewHolder;
        }


        @Override
        public void onBindViewHolder(BindingHolder holder, int position) {
            UserBasic item = mItems.get(position);
            holder.bindPicture(mContext, item);
            holder.itemView.setTag(item);

        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        @Override
        public long getItemId(int position){
            return mItems.get(position).hashCode();
        }

        @Override
        public void onClick(final View v) {

            // Give some time to the ripple to finish the effect
            if (onCardClickListener != null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onCardClickListener.onCardClick(v, (UserBasic) v.getTag());
                    }
                }, 200);
            }
        }


        static class BindingHolder extends RecyclerView.ViewHolder {
            // region binding views
            ImageView userPic;
            TextView userName;
            TextView userUrl;
            CardView cardView;
            //endregion



            BindingHolder(View itemView) {
                super(itemView);
                userPic = itemView.findViewById(R.id.profile_pic);
                userName = itemView.findViewById(R.id.user_name);
                userUrl = itemView.findViewById(R.id.user_url);
                cardView = itemView.findViewById(R.id.card_view);

            }

            void bindPicture(Context mContext, UserBasic user) {
                Glide.with(mContext)
                        .load(user.getAvatarUrl())
                        .apply(circleCropTransform()).into(userPic);
            }

        }

        public interface OnCardClickListener {
            void onCardClick(View view, UserBasic pictureItem);
        }
    
    
}


