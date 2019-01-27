package com.l134046zain.mentalhealthcare;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.List;

public class Exercise extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        overridePendingTransition(R.anim.pull_in_from_left,R.anim.hold);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_audiotrack2);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_timelapse);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_video_library);

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";


        static final String YOUTUBE_API_KEY="AIzaSyBeTiY60xtIz6mv3jQd6Lhc__uWvpodc3w";

        AudioAdapter audioAdapter;
        VideoAdapter videoAdapter;
        AnimationAdapter animationAdapter;

        SwipeRefreshLayout myAudioRefresh;
        SwipeRefreshLayout myVideoRefresh;
        SwipeRefreshLayout myAnimRefresh;

        ListView listView,listView2,listView3;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
           final int number=getArguments().getInt(ARG_SECTION_NUMBER);


            if(number==1)
            {
                View rootView = inflater.inflate(R.layout.fragment_exercise, container, false);

              myAudioRefresh=(SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);

                myAudioRefresh.setColorSchemeResources(R.color.accent,R.color.primary_dark, R.color.primary_light);
                myAudioRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh()
                    {
                        Toast.makeText(getContext(),"Refreshing Audio List !",Toast.LENGTH_LONG).show();
                        refreshAudio();
                    }
                });

                 audioAdapter = new AudioAdapter(getActivity(), R.layout.frag_audio);
                listView=(ListView) rootView.findViewById(R.id.listView);

               Audios audios=new Audios("Dota <3","https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/296318381","Dota 2 theme","https://drive.google.com/open?id=0B8By_1vHgIz7alVyV290bktRTDg");

                audioAdapter.add(audios);

               Audios audios2=new Audios("SHC <3","https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/296323284","Crusaderss...","https://drive.google.com/open?id=0B8By_1vHgIz7THdJWUR2UXNabXc");
                audioAdapter.add(audios2);

                listView.setAdapter(audioAdapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                            {
                        if(number==1)
                        {
                            Intent intent = new Intent(getActivity(), Play_Song.class);
                          Audios temp = (Audios) audioAdapter.getItem(i);
                            String _title= temp.getTitle();
                            String _description = temp.getDescription();
                            String _id = temp.getId();
                            String _download=temp.getDownload_link();

                            intent.putExtra("Title", _title);
                            intent.putExtra("Description", _description);
                            intent.putExtra("ID", _id);
                            intent.putExtra("Download",_download);

                            startActivity(intent);
                        }
                    }
                });

                return rootView;
            }
            else if(number==2)
            {
                View rootView = inflater.inflate(R.layout.anim_listview, container, false);

                animationAdapter=new AnimationAdapter(getActivity(),R.layout.frag_anim);
                listView3=(ListView) rootView.findViewById(R.id.listView3);

                myAnimRefresh=(SwipeRefreshLayout) rootView.findViewById(R.id.animswiperefresh);

                myAnimRefresh.setColorSchemeResources(R.color.accent,R.color.primary_dark, R.color.primary_light);
                myAnimRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh()
                    {
                        Toast.makeText(getContext(),"Refreshing Animation List !",Toast.LENGTH_LONG).show();
                        refreshAnimation();
                    }
                });

                Animation audios=new Animation("Sync your Breathing with this gif","file:///android_asset/splash.html","This gif will help you sync your breathing while you're in stress","https://drive.google.com/open?id=0B8By_1vHgIz7alVyV290bktRTDg");

                animationAdapter.add(audios);

                Animation audios2=new Animation("Perfectly looped Gif","file:///android_asset/raining.html","Relax yourself by watching this looped gif","https://drive.google.com/open?id=0B8By_1vHgIz7THdJWUR2UXNabXc");
                animationAdapter.add(audios2);

                listView3.setAdapter(animationAdapter);

                listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        Intent intent = new Intent(getActivity(), Play_Animation.class);
                        Animation temp = (Animation) animationAdapter.getItem(i);
                        String _title= temp.getTitle();
                        String _description = temp.getDescription();
                        String _id = temp.getId();
                        String _download=temp.getDownload_link();

                        intent.putExtra("Title", _title);
                        intent.putExtra("Description", _description);
                        intent.putExtra("ID", _id);
                        intent.putExtra("Download",_download);

                        startActivity(intent);
                    }
                });


                return rootView;
            }
            else if(number==3)
            {
                View rootView = inflater.inflate(R.layout.video_listview, container, false);

                videoAdapter = new VideoAdapter(getActivity(), R.layout.frag_video);
                listView2=(ListView) rootView.findViewById(R.id.listView2);

                myVideoRefresh=(SwipeRefreshLayout) rootView.findViewById(R.id.videoswiperefresh);

                myVideoRefresh.setColorSchemeResources(R.color.accent,R.color.primary_dark, R.color.primary_light);

                myVideoRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh()
                    {
                        Toast.makeText(getContext(),"Refreshing Video List !",Toast.LENGTH_LONG).show();
                        refreshVideo();
                    }
                });

                Videos video1=new Videos("Lengthy SHC","9foO7qWlNds","Complete version of SHC","https://drive.google.com/open?id=0B8By_1vHgIz7alVyV290bktRTDg");

               videoAdapter.add(video1);

               Videos video2=new Videos("SKYRIM","AVy7YPNP_zI","Fus du Rah...","https://drive.google.com/open?id=0B8By_1vHgIz7THdJWUR2UXNabXc");

              videoAdapter.add(video2);

                listView2.setAdapter(videoAdapter);

                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        Videos temp = (Videos) videoAdapter.getItem(i);
                        String _title= temp.getTitle();
                        String _description = temp.getDescription();
                        String _id = temp.getId();



                            Intent intent = new Intent(getActivity(), Play_Video.class);

                            String _download=temp.getDownload_link();


                            intent.putExtra("Title", _title);
                            intent.putExtra("Description", _description);
                            intent.putExtra("ID", _id);
                            intent.putExtra("Download",_download);

                            startActivity(intent);


                    }
                });




return rootView;

            }

          else {
                View rootView = inflater.inflate(R.layout.fragment_exercise, container, false);
                return rootView;
            }
        }



        private void refreshAudio()
        {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    myAudioRefresh.setRefreshing(false);
                }
            },5000);
        }
        private void refreshVideo()

        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    myVideoRefresh.setRefreshing(false);
                }
            },5000);

        }

        private void refreshAnimation()
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    myAnimRefresh.setRefreshing(false);
                }
            },5000);

        }

    }





    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter
    {

        public SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Audios";
                case 1:
                    return "Animations";
                case 2:
                    return "Videos";
            }
            return null;
        }
    }


    @Override
    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.pull_out_to_right);
        super.onPause();
    }

}
