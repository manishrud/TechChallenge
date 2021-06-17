package com.aisle.techchallengeassets.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.aisle.techchallengeassets.MainActivity;
import com.aisle.techchallengeassets.OTP_Activity;
import com.aisle.techchallengeassets.R;
import com.aisle.techchallengeassets.databinding.FragmentHomeBinding;
import com.aisle.techchallengeassets.service.ILoginService;
import com.aisle.techchallengeassets.service.model.GeneralInfo;
import com.aisle.techchallengeassets.service.model.Likes;
import com.aisle.techchallengeassets.service.model.ListLikes;
import com.aisle.techchallengeassets.service.model.ListProfile;
import com.aisle.techchallengeassets.service.model.Login;
import com.aisle.techchallengeassets.service.model.M_Profile;
import com.aisle.techchallengeassets.service.model.Photos;
import com.aisle.techchallengeassets.service.model.UserResp;
import com.aisle.techchallengeassets.util.ApiClient;
import com.aisle.techchallengeassets.util.BlurTransformation;
import com.aisle.techchallengeassets.util.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recycler;
    private TextView name;
    private AppCompatImageView imgView;
    boolean can_see_profile;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recycler = binding.recycler;
        name = binding.textView5;
        imgView = binding.imageView;
        //final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        String token = getActivity().getIntent().getStringExtra(Constants.EXTRA_TOKEN);
        getUser(token);
//        LikesAdapter likesAdapter = new LikesAdapter(getContext(),null);
//        recycler.setAdapter(likesAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getUser(String token){

        ILoginService iLoginService = ApiClient.getClient().create(ILoginService.class);
        Call<UserResp> call = iLoginService.fetchUser(token);

        call.enqueue(new Callback<UserResp>() {
            @Override
            public void onResponse(Call<UserResp> call, Response<UserResp> response) {

                if (response.code() == 200) {

                    UserResp messegeStatus = response.body();
//                    boolean status = messegeStatus.getStatus();
                    ListProfile invites = messegeStatus.getInvites();
                    ArrayList<M_Profile> profiles = invites.getProfiles();
                    if (profiles!=null && profiles.size()>0){
                        M_Profile m_profile = profiles.get(0);
                        GeneralInfo general_information = m_profile.getGeneral_information();
                        String first_name = general_information.getFirst_name();
                        String age = general_information.getAge();
                        name.setText(first_name+", "+age);
                        List<Photos> photos = m_profile.getPhotos();
                        if(photos!=null && photos.size()>0){
                            Photos p = photos.get(0);
                            String url = p.getPhoto();
                            setLoadImage(url);
                        }
                    }


//                    for (Photos p : photos){
//                        String photo = p.getPhoto();
//                    }
                    ListLikes likes = messegeStatus.getLikes();
                    ArrayList<Likes> profileLikes = (ArrayList<Likes>) likes.getProfiles();
                    can_see_profile = likes.isCan_see_profile();
                    LikesAdapter likesAdapter = new LikesAdapter(getContext(),profileLikes);
                    recycler.setAdapter(likesAdapter);
                } else Toast.makeText(getContext(), "response code "+ response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserResp> call, Throwable t) {
                Toast.makeText(getContext(), " Failed:", Toast.LENGTH_LONG).show();
                Log.e(" Failed: ", t.toString());
            }
        });
    }

    private void setLoadImage(String url){
        Glide.with(getActivity())
                .load(url)
                .centerCrop()
                //.placeholder(R.drawable.loading_spinner)
                .into(imgView);
    }
    class LikesAdapter extends RecyclerView.Adapter<LikesAdapter.ViewHolder>{
        private Context context;
        private ArrayList<Likes> list;
        public LikesAdapter(Context context, ArrayList<Likes> list){
            this.context = context;
            this.list = list;
        }
        @NonNull
        @NotNull
        @Override
        public LikesAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.likes_layout,
                    parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull LikesAdapter.ViewHolder holder, int position) {
            Likes row = list.get(position);
            holder.txt.setText(row.getName());
            String url = row.getAvatar();
//            setLoadImage(url, holder.imageView2);
//            MultiTransformation<Bitmap> multiTransformation =
//                    new MultiTransformation<Bitmap>(new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID))
            Glide.with(getActivity())
                    .load(url)
                    .transform(new BlurTransformation(context))
                    //.placeholder(R.drawable.loading_spinner)
                    .into(holder.imageView2);
//            if (!can_see_profile){
//                holder.imageView2.setForeground(getContext().getDrawable(R.color.white_trans));
//            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView txt;
            AppCompatImageView imageView2;
            public ViewHolder(@NonNull @NotNull View itemView) {
                super(itemView);
                txt = itemView.findViewById(R.id.textView5);
                imageView2 = itemView.findViewById(R.id.imageView2);
            }
        }
    }
}