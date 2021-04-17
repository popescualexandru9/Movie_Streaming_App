package com.example.moviestreamingapp.fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.moviestreamingapp.model.CustomAdapter;
import com.example.moviestreamingapp.model.MovieModel;
import com.example.moviestreamingapp.model.ClickListeners;
import com.example.moviestreamingapp.R;
import com.example.moviestreamingapp.model.operations.GetMoviesOp;
import com.example.moviestreamingapp.model.operations.InsertMovieOp;
import com.example.moviestreamingapp.model.operations.MovieOperations;

public class FirstFragment extends Fragment implements ClickListeners, MovieOperations {
    public static String MOVIE = "movie";
    private static List <MovieModel> movieList = new ArrayList<> ();
    private View View;

    public FirstFragment() { super(R.layout.fragment_first);  }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.app_name));
        View=view;

        insertAllMovies();
        new GetMoviesOp(this).execute();
    }

    @Override
    public void infoButton(MovieModel item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(MOVIE,  item);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                .addToBackStack(null);

        fragmentTransaction.commit();
    }

    @Override
    public void playFab() {
        String CHANNEL_ID = "channel";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("The movie has started!")
                .setContentText(getString(R.string.notifDesc))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = requireActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }

    @Override
    public void insertMovies(String result) {
        Log.v("Insert Movies",result);
    }

    @Override
    public void getMovies(ArrayList<MovieModel> movies) {
        movieList=movies;

        CustomAdapter adapter = new CustomAdapter(movieList, this);
        RecyclerView rv = View.findViewById(R.id.recycler_view);
        rv.setAdapter(adapter);
    }

    private void insertAllMovies(){
        MovieModel movie1 = new MovieModel(
                1,
                "The Shawshank Redemption",
                142,
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                "Chronicles the experiences of a formerly successful banker as a prisoner in the gloomy jailhouse of Shawshank after being found guilty of a crime he did not commit. The film portrays the man's unique way of dealing with his new, torturous life; along the way he befriends a number of fellow prisoners, most notably a wise long-term inmate named Red.",
                R.drawable.the_shawnshank_thumbnail,
                R.drawable.the_shawnshank_cover,
                "14 Oct 1994",
                new String[] {"Drama"}
        );

        MovieModel movie2 = new MovieModel(
                2,
                "The Godfather",
                175,
                "An organized crime dynasty's aging patriarch transfers control of his clandestine empire to his reluctant son.",
                "The Godfather \"Don\" Vito Corleone is the head of the Corleone mafia family in New York. He is at the event of his daughter's wedding. Michael, Vito's youngest son and a decorated WW II Marine is also present at the wedding. Michael seems to be uninterested in being a part of the family business. Vito is a powerful man, and is kind to all those who give him respect but is ruthless against those who do not. But when a powerful and treacherous rival wants to sell drugs and needs the Don's influence for the same, Vito refuses to do it. What follows is a clash between Vito's fading old values and the new ways which may cause Michael to do the thing he was most reluctant in doing and wage a mob war against all the other mafia families which could tear the Corleone family apart.",
                R.drawable.the_god_father_thumbnail,
                R.drawable.the_god_father_cover,
                "12 Mar 1972",
                new String[] {"Crime","Drama"}
        );

        MovieModel movie3 = new MovieModel(
                3,
                "The Godfather: Part II",
                202,
                "The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.",
                "The continuing saga of the Corleone crime family tells the story of a young Vito Corleone growing up in Sicily and in 1910s New York; and follows Michael Corleone in the 1950s as he attempts to expand the family business into Las Vegas, Hollywood and Cuba.",
                R.drawable.the_god_father2_thumbnail,
                R.drawable.the_god_father2_cover,
                "18 Dec 1974",
                new String[] {"Crime","Drama"}
        );

        MovieModel movie4 = new MovieModel(
                4,
                "The Dark Knight",
                152,
                "An organized crime dynasty's aging patriarch transfers control of his clandestine empire to his reluctant son.",
                "Set within a year after the events of Batman Begins (2005), Batman, Lieutenant James Gordon, and new District Attorney Harvey Dent successfully begin to round up the criminals that plague Gotham City, until a mysterious and sadistic criminal mastermind known only as \"The Joker\" appears in Gotham, creating a new wave of chaos. Batman's struggle against The Joker becomes deeply personal, forcing him to \"confront everything he believes\" and improve his technology to stop him. A love triangle develops between Bruce Wayne, Dent, and Rachel Dawes.",
                R.drawable.the_dark_night_thumbnail,
                R.drawable.the_dark_night_cover,
                "12 Jul 2008",
                new String[] {"Action","Crime","Drama","Thriller"}
        );
        MovieModel movie5 = new MovieModel(
                5,
                "Schindler's List",
                195,
                "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.",
                "Oskar Schindler is a vain and greedy German businessman who becomes an unlikely humanitarian amid the barbaric German Nazi reign when he feels compelled to turn his factory into a refuge for Jews. Based on the true story of Oskar Schindler who managed to save about 1100 Jews from being gassed at the Auschwitz concentration camp, it is a testament to the good in all of us.",
                R.drawable.schindlers_list_thumbnail,
                R.drawable.schindlers_list_cover,
                "4 Feb 1994",
                new String[] {"Biography","Drama","History"}
        );

        MovieModel[] movies= new MovieModel[] {movie1,movie2,movie3,movie4,movie5};
        new InsertMovieOp(this).execute(movies);
    }
}
