package com.example.covid_19tracker;

import android.os.Bundle;
import android.view.MenuItem;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.covid_19tracker.Adapter.FaqAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FaqActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> title;
    List<String> answers;
    FaqAdapter faqAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("FAQ");

        recyclerView = findViewById(R.id.FaqRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        title = new ArrayList<>();
        answers = new ArrayList<>();

        setQuestionsAndAnswers();
        faqAdapter = new FaqAdapter(title, answers, this);
        recyclerView.setAdapter(faqAdapter);


    }

    private void setQuestionsAndAnswers() {

        title.add("What is coronavirus ?");
        answers.add("Coronaviruses are a large family of viruses which may cause illness in animals or humans.  In humans, several coronaviruses are known to cause respiratory infections ranging from the common cold to more severe diseases such as Middle East Respiratory Syndrome (MERS) and Severe Acute Respiratory Syndrome (SARS). The most recently discovered coronavirus causes coronavirus disease COVID-19.");

        title.add("What is Covid-19?");
        answers.add("COVID-19 is the infectious disease caused by the most recently discovered coronavirus. This new virus and disease were unknown before the outbreak began in Wuhan, China, in December 2019. COVID-19 is now a pandemic affecting many countries globally.");

        title.add("What are the symptoms of COVID-19 ?");
        answers.add("The most common symptoms of COVID-19 are fever, dry cough, and tiredness. Some patients may have aches and pains, nasal congestion, sore throat or diarrhea. These symptoms are usually mild and begin gradually. Some people become infected but only have very mild symptoms. Most people (about 80%) recover from the disease without needing hospital treatment. Around 1 out of every 5 people who gets COVID-19 becomes seriously ill and develops difficulty breathing. Older people, and those with underlying medical problems like high blood pressure, heart and lung problems, diabetes, or cancer , are at higher risk of developing serious illness. However anyone can catch COVID-19 and become seriously ill. Even people with very mild symptoms of COVID-19 can transmit the virus. People of all ages who experience fever, cough and difficulty breathing should seek medical attention.");

        title.add("What should I do if I have COVID-19 symptoms and when should i seek medical care ?");
        answers.add("If you have minor symptoms, such as a slight cough or a mild fever, there is generally no need to seek medical care. Stay at home, self-isolate and monitor your symptoms. Follow national guidance on self-isolation.\n" +
                "\n" +
                "However, if you live in an area with malaria or dengue fever it is important that you do not ignore symptoms of fever.  Seek medical help.  When you attend the health facility wear a mask if possible, keep at least 1 metre distance from other people and do not touch surfaces with your hands. If it is a child who is sick help the child stick to this advice.\n" +
                "\n" +
                "Seek immediate medical care if you have difficulty breathing or pain/pressure in the chest. If possible, call your health care provider in advance, so he/she can direct you to the right health facility.");

        title.add("How does COVID-19 spread ?");
        answers.add("People can catch COVID-19 from others who have the virus. The disease spreads primarily from person to person through small droplets from the nose or mouth, which are expelled when a person with COVID-19 coughs, sneezes, or speaks. These droplets are relatively heavy, do not travel far and quickly sink to the ground. People can catch COVID-19 if they breathe in these droplets from a person infected with the virus. This is why it is important to stay at least 1 metre (3 feet) away from others. These droplets can land on objects and surfaces around the person such as tables, doorknobs and handrails. People can become infected by touching these objects or surfaces, then touching their eyes, nose or mouth. This is why it is important to wash your hands regularly with soap and water or clean with alcohol-based hand rub.\n" +
                "\n" +
                "WHO is assessing ongoing research on the ways that COVID-19 is spread and will continue to share updated findings.");

        title.add("How can we protect others and ourselves if we don't know who is infected");
        answers.add("Practicing hand and respiratory hygiene is important at ALL times and is the best way to protect others and yourself.\n" +
                "\n" +
                "When possible maintain at least a 1 metre (3 feet) distance between yourself and others. This is especially important if you are standing by someone who is coughing or sneezing. Since some infected persons may not yet be exhibiting symptoms or their symptoms may be mild, maintaining a physical distance with everyone is a good idea if you are in an area where COVID-19 is circulating.");

        title.add("What should I do if I have come in close contact with someone who has COVID-19 ");
        answers.add("If you have been in close contact with someone with COVID-19, you may be infected.\n" +
                "\n" +
                "Close contact means that you live with or have been in settings of less than 1 metre from those who have the disease. In these cases, it is best to stay at home.\n" +
                "\n" +
                "However, if you live in an area with malaria or dengue fever it is important that you do not ignore symptoms of fever. Seek medical help. When you attend the health facility wear a mask if possible, keep at least 1 metre distant from other people and do not touch surfaces with your hands. If it is a child who is sick help the child stick to this advice.\n" +
                "\n" +
                "If you do not live in an area with malaria or dengue fever please do the following:\n" +
                "\n" +
                "If you become ill, even with very mild symptoms you must self-isolate\n" +
                "Even if you don’t think you have been exposed to COVID-19 but develop symptoms, then self-isolate and monitor yourself\n" +
                "You are more likely to infect others in the early stages of the disease when you just have mild symptoms, therefore early self-isolation is very important.\n" +
                "If you do not have symptoms, but have been exposed to an infected person, self-quarantine for 14 days.\n" +
                "If you have definitely had COVID-19 (confirmed by a test) self-isolate for 14 days even after symptoms have disappeared as a precautionary measure – it is not yet known exactly how long people remain infectious after they have recovered. Follow national advice on self-isolation.");

        title.add("What does it mean to self isolate ?");
        answers.add("Self-isolation is an important measure taken by those who have COVID-19 symptoms to avoid infecting others in the community, including family members." +
                "\n" +
                "Self-isolation is when a person who is experiencing fever, cough or other COVID-19 symptoms stays at home and does not go to work, school or public places. This can be voluntarily or based on his/her health care provider’s recommendation. However, if you live in an area with malaria or dengue fever it is important that you do not ignore symptoms of fever. Seek medical help. When you attend the health facility wear a mask if possible, keep at least 1 metre distant from other people and do not touch surfaces with your hands. If it is a child who is sick help the child stick to this advice." +
                "\n" +
                "If you do not live in an area with malaria or dengue fever please do the following:\n" +
                "\n" +
                "->If a person is in self-isolation, it is because he/she is ill but not severely ill (requiring medical attention)\n" +
                "\n" +
                "-> have a large, well-ventilated with hand-hygiene and toilet facilities\n" +
                "-> If this is not possible, place beds at least 1 metre apart\n" +
                "-> Keep at least 1 metre (3 feet) from others, even from your family members\n" +
                "-> Monitor your symptoms daily\n" +
                "-> Isolate for 14 days, even if you feel healthy\n" +
                "-> If you develop difficulty breathing, contact your healthcare provider immediately – call them first if possible\n" +
                "-> Stay positive and energized by keeping in touch with loved ones by phone or online, and by exercising yourself at home.");

        title.add("Is there a vaccine, drug or treatment for COVID-19");
        answers.add("Not yet. To date, there is no vaccine and no specific antiviral medicines against COVID-19. However, .people, particularly those with serious illness, may need to be hospitalized so that they can receive life-saving treatment for complications.. Most patients recover thanks to such care. \n" +
                "\n" +
                "Possible vaccines and some specific drug treatments are currently under investigation. They are being tested through clinical trials. WHO is coordinating efforts to develop vaccines and medicines to prevent and treat COVID-19. \n" +
                "\n" +
                "The most effective ways to protect yourself and others against COVID-19 are to:\n" +
                "\n" +
                "-> Clean your hands frequently and thoroughly\n" +
                "-> Avoid touching your eyes, mouth and nose\n" +
                "-> Cover your cough with the bend of elbow or tissue. If a tissue is used, discard it immediately and wash your hands.\n" +
                "-> Maintain a distance of at least 1 metre (3 feet) from others. ");


        title.add("How likely am I to catch COVID-19?");
        answers.add("The risk depends on where you are - and more specifically, whether" +
                "there is a COVID-19 outbreak unfolding there." +
                "For most people in most locations the risk of catching COVID-19 is" +
                "still low. However, there are now places around the world (cities or" +
                "areas) where the disease is spreading. For people living in, or visiting," +
                "these areas the risk of catching COVID-19 is higher. Governments" +
                "and health authorities are taking vigorous action every time a new" +
                "case of COVID-19 is identified. Be sure to comply with any local" +
                "restrictions on travel, movement or large gatherings. Cooperating with" +
                "disease control efforts will reduce your risk of catching or spreading" +
                "COVID-19." +
                "COVID-19 outbreaks can be contained and transmission stopped, as" +
                "has been shown in China and some other countries. Unfortunately," +
                "new outbreaks can emerge rapidly. It’s important to be aware of the" +
                "situation where you are or intend to go." +
                "Should I");

        title.add("Should I worry about COVID-19?");
        answers.add("Illness due to COVID-19 infection is generally mild, especially for" +
                "children and young adults. However, it can cause serious illness:" +
                "about 1 in every 5 people who catch it need hospital care. It is" +
                "therefore quite normal for people to worry about how the COVID-19" +
                "outbreak will affect them and their loved ones." +
                "We can channel our concerns into actions to protect ourselves, our" +
                "loved ones and our communities. First and foremost among these" +
                "actions is regular and thorough hand-washing and good respiratory" +
                "hygiene. Secondly, keep informed and follow the advice of the local" +
                "health authorities including any restrictions put in place on travel," +
                "movement and gatherings.");

        title.add("Are antibiotics effective in preventing or treating the COVID-19?");
        answers.add("No. Antibiotics do not work against viruses, they only work on" +
                "bacterial infections. COVID-19 is caused by a virus, so antibiotics do" +
                "not work. Antibiotics should not be used as a means of prevention or" +
                "treatment of COVID-19. They should only be used as directed by a" +
                "physician to treat a bacterial infection.");

        title.add("How long is the incubation period for COVID-19?");
        answers.add("The “incubation period” means the time between catching the virus" +
                "and beginning to have symptoms of the disease. Most estimates of" +
                "the incubation period for COVID-19 range from 1-14 days, most" +
                "commonly around five days. These estimates will be updated as more" +
                "data become available.");

        title.add("Can humans become infected with the COVID-19 from an animal" +
                "source?");
        answers.add("Coronaviruses are a large family of viruses that are common in" +
                "animals. Occasionally, people get infected with these viruses which" +
                "may then spread to other people. For example, SARS-CoV was" +
                "associated with civet cats and MERS-CoV is transmitted by" +
                "dromedary camels. Possible animal sources of COVID-19 have not" +
                "yet been confirmed." +
                "To protect yourself, such as when visiting live animal markets, avoid" +
                "direct contact with animals and surfaces in contact with animals." +
                "Ensure good food safety practices at all times. Handle raw meat, milk" +
                "or animal organs with care to avoid contamination of uncooked foods" +
                "and avoid consuming raw or undercooked animal products.");

        title.add("Can I catch COVID-19 from my pet?");
        answers.add("While there has been one instance of a dog being infected in Hong" +
                "Kong, to date, there is no evidence that a dog, cat or any pet can" +
                "transmit COVID-19. COVID-19 is mainly spread through droplets" +
                "produced when an infected person coughs, sneezes, or speaks. To" +
                "protect yourself, clean your hands frequently and thoroughly." +
                "We continues to monitor the latest research on this and other COVID-" +
                "19 topics and will update as new findings are available.");

        title.add("How long does the virus survive on surfaces?");
        answers.add("It is not certain how long the virus that causes COVID-19 survives on" +
                "surfaces, but it seems to behave like other corona viruses. Studies" +
                "suggest that corona viruses (including preliminary information on the" +
                "COVID-19 virus) may persist on surfaces for a few hours or up to" +
                "several days. This may vary under different conditions (e.g. type of" +
                "surface, temperature or humidity of the environment)." +
                "If you think a surface may be infected, clean it with simple disinfectant" +
                "to kill the virus and protect yourself and others. Clean your hands with" +
                "an alcohol-based hand rub or wash them with soap and water. Avoid" +
                "touching your eyes, mouth, or nose.");

        title.add("Is there anything I should not do?");
        answers.add("The following measures ARE NOT effective against COVID-2019 and" +
                "can be harmful:" +
                "• Smoking\n" +
                "• Wearing multiple masks\n" +
                "• Taking antibiotics (See question 10 \"Are there any medicines of" +
                "therapies that can prevent or cure COVID-19?\")" +
                "In any case, if you have fever, cough and difficulty breathing" +
                "seek medical care early to reduce the risk of developing a more" +
                "severe infection and be sure to share your recent travel history with" +
                "your health care provider.");

        title.add("How to grocery shop safely?");
        answers.add("When grocery shopping, keep at least 1-metre distance from others and avoid touching your eyes, mouth and nose. If possible, sanitize the handles of shopping trolleys or baskets before shopping. Once home, wash your hands thoroughly and also after handling and storing your purchased products."
                +
                "There is currently no confirmed case of COVID-19 transmitted through food or food packaging.");

        title.add("How to wash vegetable and fruits?");
        answers.add("Fruits and vegetables are important components of a healthy diet. Wash them the same way you should do under any circumstance: before handling them, wash your hands with soap and water. Then, wash fruits and vegetables thoroughly with clean water, especially if you eat them raw.");

        title.add("DATA SOURCE");
        answers.add("All th above data has been collected from WHO Website ");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(this);
    }
}
