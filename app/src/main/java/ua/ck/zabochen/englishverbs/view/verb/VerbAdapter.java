package ua.ck.zabochen.englishverbs.view.verb;

import android.content.Context;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import ua.ck.zabochen.englishverbs.MainApp;
import ua.ck.zabochen.englishverbs.R;
import ua.ck.zabochen.englishverbs.model.realm.Verb;
import ua.ck.zabochen.englishverbs.utils.Tools;

public class VerbAdapter extends RecyclerView.Adapter<VerbAdapter.VerbViewHolder> {

    private static final String TAG = VerbAdapter.class.getSimpleName();

    @Inject Context mContext;

    private ArrayList<Verb> verbs = new ArrayList<>();

    public VerbAdapter(ArrayList<Verb> verbs) {
        this.verbs = verbs;
        MainApp.getAppComponent().inject(this);
    }

    @Override
    public VerbViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VerbViewHolder(mContext, LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_verb, parent, false));
    }

    @Override
    public void onBindViewHolder(VerbViewHolder holder, int position) {
        holder.bind(verbs.get(position));
    }

    @Override
    public int getItemCount() {
        return verbs.size() > 0
                ? verbs.size()
                : 0;
    }

    static class VerbViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private ImageView verbImage;
        private TextView verb;
        private TextView verbTranscription;
        private TextView verbExample;
        private RoundedBitmapDrawable noImage;

        public VerbViewHolder(Context context, View itemView) {
            super(itemView);
            this.mContext = context;
            this.verbImage = itemView.findViewById(R.id.adapterVerb_image);
            this.verb = itemView.findViewById(R.id.adapterVerb_verb);
            this.verbTranscription = itemView.findViewById(R.id.adapterVerb_verbTranscription);
            this.verbExample = itemView.findViewById(R.id.adapterVerb_verbExample);
            this.noImage = Tools.getRoundedImageFromResources(context, R.drawable.no_image);
        }

        void bind(Verb verb) {

            // Verb Image
            if (verb.getVerbImage() != null) {
                verbImage.setImageDrawable(Tools.getRoundedImageFromAssets(mContext, verb.getVerbImage()));
            } else {
                verbImage.setImageDrawable(noImage);
            }

            // Verb
            this.verb.setText(verb.getVerb());

            // Verb Transcription
            this.verbTranscription.setText("[" + verb.getVerbTranscription() + "]");

            // Verb Examples
            if (verb.getVerbExamples().size() > 0) {
                this.verbExample.setText(verb.getVerbExamples().get(0).getExample());
            } else {
                this.verbExample.setText("I will do just as you advise - Я сделаю как раз так, как вы советуете");
            }
        }
    }

}
