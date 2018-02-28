package vn.edu.tdc.tracnghiem;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.tdc.tracnghiem.data_models.QuestionAbtract;
import vn.edu.tdc.tracnghiem.data_models.QuestionType1;
import vn.edu.tdc.tracnghiem.data_models.QuestionType2;
import vn.edu.tdc.tracnghiem.fragments.MultiChoice1Fragment;
import vn.edu.tdc.tracnghiem.fragments.MultiChoice2Fragment;
import vn.edu.tdc.tracnghiem.fragments.MyFragmentAbstract;
import vn.edu.tdc.tracnghiem.fragments.QuestionMatchFragment;
import vn.edu.tdc.tracnghiem.fragments.ResultFragment;
import vn.edu.tdc.tracnghiem.fragments.TrueFalseFragment1;
import vn.edu.tdc.tracnghiem.fragments.TrueFalseFragment2;

public class Cau1Activity extends AppCompatActivity {
    // Declaring variables
    private ArrayList<QuestionAbtract> mQuestions = new ArrayList<QuestionAbtract>();
    private ArrayList<String> mDrawerList = new ArrayList<String>();

    private int questionID = 0;
    private boolean finised = false;
    private FragmentTransaction fragmentTransaction;
    private MyFragmentAbstract fragment;
    private Button btnPrevious;
    private Button btnFinish;
    private Button btnNext;
    private ArrayAdapter<String> adapter;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        FrameLayout container = (FrameLayout) findViewById(R.id.container);

        getLayoutInflater().inflate(R.layout.cau1_layout, container);


        //Initiation
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnFinish = (Button) findViewById(R.id.btnFinish);
        btnNext = (Button) findViewById(R.id.btnNext);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawLayout);

        initiationData();

        int i = 1;
        for (QuestionAbtract question : mQuestions) {
            mDrawerList.add("Câu hỏi số " + i++);
        }

        final ListView drawerList = (ListView) findViewById(R.id.drawer);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDrawerList);
        drawerList.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(Cau1Activity.this, drawerLayout, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        updateUI();

        // Processing

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.updateQuestionChosen();
                if (questionID < mQuestions.size()) {
                    questionID++;
                }
                updateUI();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.updateQuestionChosen();
                if (questionID > 0) {
                    questionID--;
                }
                updateUI();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finised) {
                    fragment.updateQuestionChosen();
                    finised = true;
                    updateUI();
                } else {
                    finish();
                }
            }
        });

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                questionID = position;
                drawerLayout.closeDrawer(drawerList);
                fragment.updateQuestionChosen();
                updateUI();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateUI() {

        if (questionID == 0) {
            btnPrevious.setEnabled(false);
            btnNext.setEnabled(true);
        } else if (questionID == mQuestions.size() - 1) {
            btnPrevious.setEnabled(true);
            btnNext.setEnabled(false);
        } else {
            btnPrevious.setEnabled(true);
            btnNext.setEnabled(true);
        }

        if (!finised) {
            if (mQuestions.get(questionID).getType().equalsIgnoreCase(QuestionAbtract.CAU_NHIEU_LUA_CHON_NHIEU_DAP_AN)) {
                if (getFragmentManager().findFragmentByTag(questionID + "") == null) {
                    fragment = new MultiChoice1Fragment();
                } else {
                    fragment = (MultiChoice1Fragment) getFragmentManager().findFragmentByTag(questionID + "");
                }
            } else if (mQuestions.get(questionID).getType().equalsIgnoreCase(QuestionAbtract.CAU_NHIEU_LUA_CHON_MOT_DAP_AN)) {
                if (getFragmentManager().findFragmentByTag(questionID + "") == null) {
                    fragment = new MultiChoice2Fragment();
                } else {
                    fragment = (MultiChoice2Fragment) getFragmentManager().findFragmentByTag(questionID + "");
                }
            } else if (mQuestions.get(questionID).getType().equalsIgnoreCase(QuestionAbtract.CAU_GHEP_DOI)) {
                if (getFragmentManager().findFragmentByTag(questionID + "") == null) {
                    fragment = new QuestionMatchFragment();
                } else {
                    fragment = (QuestionMatchFragment) getFragmentManager().findFragmentByTag(questionID + "");
                }
            } else if (mQuestions.get(questionID).getType().equalsIgnoreCase(QuestionAbtract.CAU_DUNG_SAI)) {
                if (questionID % 2 == 1) {
                    if (getFragmentManager().findFragmentByTag(questionID + "") == null) {
                        fragment = new TrueFalseFragment1();
                    } else {
                        fragment = (TrueFalseFragment1) getFragmentManager().findFragmentByTag(questionID + "");
                    }
                } else {
                    if (getFragmentManager().findFragmentByTag(questionID + "") == null) {
                        fragment = new TrueFalseFragment2();
                    } else {
                        fragment = (TrueFalseFragment2) getFragmentManager().findFragmentByTag(questionID + "");
                    }
                }
            }

            fragment.setQuestion(mQuestions.get(questionID));
            fragment.setPosition(questionID);

            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, fragment, questionID + "");
            if (getFragmentManager().findFragmentByTag(questionID + "") == null) {
                fragmentTransaction.addToBackStack(null);
            }
            fragmentTransaction.commit();
        } else {
            fragment = new ResultFragment();
            ((ResultFragment) fragment).setmQuestions(mQuestions);

            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.commit();
        }
    }

    private void initiationData() {
        // Initiating variables
        // Quest 1
        String des = "Trong các mô hình mạng dưới đây, mô hình nào được dùng phổ biến hiện nay:";
        ArrayList<String> questionItems = new ArrayList<String>();
        questionItems.add("Peer - to - Peer");
        questionItems.add("Remote Access");
        questionItems.add("Terminal Mainframe");
        questionItems.add("Client - Server");
        ArrayList<Integer> questionAnswes = new ArrayList<Integer>();
        questionAnswes.add(0);
        questionAnswes.add(3);
        ArrayList<Integer> question1Chosen = new ArrayList<Integer>();
        QuestionType1 question1 = new QuestionType1(des, questionItems, questionAnswes, question1Chosen, QuestionAbtract.CAU_NHIEU_LUA_CHON_NHIEU_DAP_AN);

        // Quest 2
        des = "Dịch vụ mạng DNS dùng để làm gì?";
        ArrayList<String> question2Items = new ArrayList<String>();
        question2Items.add("Cấp địa chỉ cho các máy trạm");
        question2Items.add("Phân giải tên miền và địa chỉ IP");
        question2Items.add("Truyền file và dữ liệu");
        question2Items.add("Gửi thư điện tử");
        ArrayList<Integer> question2Answes = new ArrayList<Integer>();
        question2Answes.add(1);
        ArrayList<Integer> question2Chosen = new ArrayList<Integer>();
        QuestionType1 question2 = new QuestionType1(des, question2Items, question2Answes, question2Chosen, QuestionAbtract.CAU_NHIEU_LUA_CHON_MOT_DAP_AN);

        // Quest 3
        des = "Hãy tìm những đáp án đúng cho các mệnh đề dưới đây bằng cách nhấp chọn ô xổ xuống bên tay phải và lựa chọn câu trả lời";
        ArrayList<String> question3Items = new ArrayList<String>();
        question3Items.add("Giao thức DHCP có thể cấp được...");
        question3Items.add("Mô hình mạng dùng nhiều nhất...");
        question3Items.add("Dịch vụ DNS dùng để ...");

        ArrayList<String> question3Choices = new ArrayList<String>();
        question3Choices.add("Địa chỉ Mac");
        question3Choices.add("Địa chỉ IP");
        question3Choices.add("Subnet Mask");
        question3Choices.add("Client - Server");
        question3Choices.add("Phân giải tên và địa chỉ");

        ArrayList<Integer> question3Answes = new ArrayList<Integer>();
        question3Answes.add(1);
        question3Answes.add(3);
        question3Answes.add(4);

        ArrayList<Integer> question3Chosen = new ArrayList<Integer>();

        QuestionType2 question3 = new QuestionType2(des, question3Items, question3Choices, question3Answes, question3Chosen);

        // Quest 4
        des = "Hãy lựa chọn ĐÚNG hay SAI cho những mệnh đề dưới đây bằng cách nhấp chuột vào nút bên tay phải";
        ArrayList<String> question4Items = new ArrayList<String>();
        question4Items.add("Giao thức DHCP có thể cấp được địa chỉ IP");
        question4Items.add("Mô hình mạng phổ biến: Terminal - Mainframe");
        question4Items.add("Dịch vụ DNS dùng để phân giải tên và địa chỉ");

        ArrayList<Integer> question4Answes = new ArrayList<Integer>();
        question4Answes.add(1);
        question4Answes.add(0);
        question4Answes.add(1);

        ArrayList<Integer> question4Chosen = new ArrayList<Integer>();

        QuestionType1 question4 = new QuestionType1(des, question4Items, question4Answes, question4Chosen, QuestionAbtract.CAU_DUNG_SAI);

        // Quest 5
        des = "Hãy lựa chọn ĐÚNG hay SAI cho những mệnh đề dưới đây bằng cách kéo thanh trượt bên tay phải";
        ArrayList<String> question5Items = new ArrayList<String>();
        question5Items.add("Mô hình mạng phổ biến: Terminal - Mainframe");
        question5Items.add("Giao thức DHCP có thể cấp được địa chỉ IP");
        question5Items.add("Dịch vụ DNS dùng để phân giải tên và địa chỉ");

        ArrayList<Integer> question5Answes = new ArrayList<Integer>();
        question5Answes.add(0);
        question5Answes.add(1);
        question5Answes.add(1);

        ArrayList<Integer> question5Chosen = new ArrayList<Integer>();

        QuestionType1 question5 = new QuestionType1(des, question5Items, question5Answes, question5Chosen, QuestionAbtract.CAU_DUNG_SAI);

        // Quest 6
        des = "Hãy lựa chọn ĐÚNG hay SAI cho những mệnh đề dưới đây bằng cách kéo thanh trượt bên tay phải";
        ArrayList<String> question6Items = new ArrayList<String>();
        question6Items.add("Mô hình mạng phổ biến: Terminal - Mainframe");
        question6Items.add("Giao thức DHCP có thể cấp được địa chỉ IP");
        question6Items.add("Dịch vụ DNS dùng để phân giải tên và địa chỉ");

        ArrayList<Integer> question6Answes = new ArrayList<Integer>();
        question6Answes.add(0);
        question6Answes.add(1);
        question6Answes.add(1);

        ArrayList<Integer> question6Chosen = new ArrayList<Integer>();

        QuestionType1 question6 = new QuestionType1(des, question6Items, question6Answes, question6Chosen, QuestionAbtract.CAU_DUNG_SAI);

        mQuestions.add(question1);
        mQuestions.add(question2);
        mQuestions.add(question3);
        mQuestions.add(question4);
        mQuestions.add(question5);
        mQuestions.add(question6);
    }
}
