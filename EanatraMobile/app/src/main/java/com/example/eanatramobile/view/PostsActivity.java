package com.example.eanatramobile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.eanatramobile.R;
import com.example.eanatramobile.apiInterface.PostsApi;
import com.example.eanatramobile.apiInterface.Server;
import com.example.eanatramobile.modele.Post;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsActivity extends AppCompatActivity {

    private TextView textViewResult ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        textViewResult = findViewById(R.id.list_item);

        Retrofit retrofit = Server.getServert();
        PostsApi postsApi = retrofit.create(PostsApi.class);
//        getPosts(postsApi);
        createPost(postsApi);

    }

    private void getPosts(PostsApi postsApi){

        Call<List<Post>> call = postsApi.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText(" Code 400");
                    return;
                }

                List<Post> posts = response.body();
                String content = null ;
                for(Post post: posts){
                    content+= "ID : "+post.getId()+" \n" ;
                    content+= "title : "+post.getTitle()+" \n" ;
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void createPost(PostsApi postsApi){
        Post post = new Post(23,"new title","new texte");
        Call<Post> call = postsApi.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    return ;
                }

                Log.d("coucou",response.toString());

//                Post result = response.body();
//                String content = "" ;
//                content+= "ID : "+result.getId()+" \n" ;
//                content+= "title : "+result.getTitle()+" \n" ;
//                textViewResult.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

}