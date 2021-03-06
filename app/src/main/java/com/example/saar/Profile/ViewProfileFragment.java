package com.example.saar.Profile;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.saar.Constant;
import com.example.saar.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfileFragment extends Fragment {

    CircleImageView profileImage;
    TextView name, degree, graduation_year, branch, email, rollno, phone, fb_link, linkedin_link;
    TextView present_employer, designation, address, country, city, state, achievements, employment_type;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_view_profile, container, false);
        setHasOptionsMenu(true);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        setupUI(rootView);

        return rootView;
    }


    private void setupUI(View rootView) {
        profileImage = rootView.findViewById(R.id.profile_picture);
        name = rootView.findViewById(R.id.profile_name);
        degree = rootView.findViewById(R.id.profile_degree);
        graduation_year = rootView.findViewById(R.id.profile_graduation_year);
        branch = rootView.findViewById(R.id.branch);
        email = rootView.findViewById(R.id.email);
        rollno = rootView.findViewById(R.id.rollno);
        phone = rootView.findViewById(R.id.phone);
        fb_link = rootView.findViewById(R.id.fb_link);
        linkedin_link = rootView.findViewById(R.id.linkedin_link);
        present_employer = rootView.findViewById(R.id.present_employer);
        employment_type = rootView.findViewById(R.id.employment_type);
        designation = rootView.findViewById(R.id.designation);
        address = rootView.findViewById(R.id.address);
        country = rootView.findViewById(R.id.country);
        city = rootView.findViewById(R.id.city);
        state = rootView.findViewById(R.id.state);
        achievements = rootView.findViewById(R.id.achievements);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.profile_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_edit_profile:
                Fragment fragment = new EditProfileFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_profile_container, fragment); // give your fragment container id in first parameter
                ft.commit();
                return false;
        }
        return onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.view_profile_fragment);
        setHasOptionsMenu(true);
        fillDetails();
    }

    private void fillDetails() {
        if (preferences.getBoolean(Constant.LOGIN_STATUS, false)) {
            String full_name = preferences.getString(Constant.FIRST_NAME, "") + " " + preferences.getString(Constant.LAST_NAME, "");
            name.setText(full_name);
            degree.setText(preferences.getString(Constant.DEGREE, ""));
            graduation_year.setText(preferences.getString(Constant.GRADUATION_YEAR, ""));
            branch.setText(preferences.getString(Constant.DEPARTMENT, ""));
            email.setText(preferences.getString(Constant.EMAIL, ""));
            rollno.setText(preferences.getString(Constant.ROLLNO, ""));
            phone.setText(preferences.getString(Constant.PHONE, ""));

            //checking for empty fields
            String fb_link_text = preferences.getString(Constant.FB_LINK, "");
            if (fb_link_text.isEmpty()) {
                getView().findViewById(R.id.fb_link_layout).setVisibility(View.GONE);
                getView().findViewById(R.id.fb_link_line).setVisibility(View.GONE);
            } else
                fb_link.setText(fb_link_text);

            String linkedin_link_text = preferences.getString(Constant.LINKEDIN_LINK, "");
            if (linkedin_link_text.isEmpty()) {
                getView().findViewById(R.id.linkedin_link_layout).setVisibility(View.GONE);
                getView().findViewById(R.id.linkedin_link_line).setVisibility(View.GONE);
            } else
                linkedin_link.setText(linkedin_link_text);

            String present_employer_text = preferences.getString(Constant.PRESENT_EMPLOYER, "");
            if (present_employer_text.isEmpty()) {
                getView().findViewById(R.id.present_employer_layout).setVisibility(View.GONE);
                getView().findViewById(R.id.present_employer_line).setVisibility(View.GONE);
            } else
                present_employer.setText(present_employer_text);

            String employment_type_text = preferences.getString(Constant.EMPLOYEMENT_TYPE, "");
            if (employment_type_text.isEmpty()) {
                getView().findViewById(R.id.employment_type_layout).setVisibility(View.GONE);
                getView().findViewById(R.id.employment_type_line).setVisibility(View.GONE);
            } else
                employment_type.setText(employment_type_text);

            String designation_text = preferences.getString(Constant.DESIGNATION, "");
            if (designation_text.isEmpty()) {
                getView().findViewById(R.id.designation_layout).setVisibility(View.GONE);
                getView().findViewById(R.id.designation_line).setVisibility(View.GONE);
            } else
                designation.setText(designation_text);

            String address_text = preferences.getString(Constant.ADDRESS, "");
            if (address_text.isEmpty()) {
                getView().findViewById(R.id.address_layout).setVisibility(View.GONE);
                getView().findViewById(R.id.address_line).setVisibility(View.GONE);
            } else
                address.setText(address_text);

            String country_text = preferences.getString(Constant.COUNTRY, "");
            if (country_text.isEmpty()) {
                getView().findViewById(R.id.country_layout).setVisibility(View.GONE);
                getView().findViewById(R.id.country_line).setVisibility(View.GONE);
            } else
                country.setText(country_text);

            String city_text = preferences.getString(Constant.CITY, "");
            if (city_text.isEmpty()) {
                getView().findViewById(R.id.city_layout).setVisibility(View.GONE);
                getView().findViewById(R.id.city_line).setVisibility(View.GONE);
            } else
                city.setText(city_text);

            String state_text = preferences.getString(Constant.STATE, "");
            if (state_text.isEmpty()) {
                getView().findViewById(R.id.state_layout).setVisibility(View.GONE);
                getView().findViewById(R.id.state_line).setVisibility(View.GONE);
            } else
                state.setText(state_text);

            String achievements_text = preferences.getString(Constant.ACHIEVEMENTS, "");
            if (achievements_text.isEmpty()) {
                getView().findViewById(R.id.achievements_layout).setVisibility(View.GONE);
            } else
                achievements.setText(achievements_text);

            Glide.with(getActivity())
                    .load(preferences.getString(Constant.IMG_URL, ""))
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .placeholder(R.drawable.ic_account_circle_black_48dp)
                    .into(profileImage);
        }
    }
}
