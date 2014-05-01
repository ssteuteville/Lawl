import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Garreth on 5/1/2014.
 */
public class Profile implements Parcelable {
    private String name;
    private int rankedWins;
    private int normalWins;
    private int rankedLosses;
    private int normalLosses;
    private String previousRank;
    private String currentRank;
    private int totalChampionKills;
    private int averageKDA;
    private int totalDoubleKills;
    private int totalTripleKills;
    private int totalQuadraKills;
    private int totalPentaKills;


    //Add and alter parameters to make it easier for calling this constructor
    public Profile(String name, int rankedWins, int normalWins, int rankedLosses, int normalLosses, String previousRank
                   , String currentRank, int totalChampionKills, int averageKDA, int totalDoubleKills, int totalTripleKills
                   , int totalQuadraKills, int totalPentaKills)
    {
        this.name = name;
        this.rankedWins = rankedWins;
        this.normalWins = normalWins;
        this.rankedLosses = rankedLosses;
        this.normalLosses = normalLosses;
        this.previousRank = previousRank;
        this.currentRank = currentRank;
        this.totalChampionKills = totalChampionKills;
        this.averageKDA = averageKDA;
        this.totalDoubleKills = totalDoubleKills;
        this.totalTripleKills = totalTripleKills;
        this.totalQuadraKills = totalQuadraKills;
        this.totalPentaKills = totalPentaKills;
    }
    public String getName()
    {
        return name;
    }

    public int getRankedWins()
    {
        return rankedWins;
    }

    public int getNormalWins() { return normalWins; }

    public int getRankedLosses() {return rankedLosses;}

    public int getNormalLosses() {return normalLosses;}

    public String getPreviousRank()
    {
        return previousRank;
    }

    public String getCurrentRank()
    {
        return currentRank;
    }

    public int getTotalChampionKills() { return  totalChampionKills;}

    public int getAverageKDA() { return averageKDA;}

    public int getTotalDoubleKills() { return totalDoubleKills; }

    public int getTotalTripleKills() {return totalTripleKills;}

    public int getTotalQuadraKills() { return totalQuadraKills; }

    public int getTotalPentaKills() { return totalPentaKills; }

//------------------------------------------------------------------------

    public void setName(String name) { this.name = name; }

    public void setRankedWins(int rank_wins) { this.rankedWins =rank_wins; }

    public void setNormalWins(int norm_wins) {this.normalWins = norm_wins;}

    public void setRankedLosses(int rank_losses) { this.rankedLosses = rank_losses;}

    public void setNormalLosses(int norm_losses) { this.normalLosses = norm_losses;}

    public void setPreviousRank(String rank) { this.previousRank = rank; }

    public void setCurrentRank(String rank) { this.currentRank = rank; }

    public void setTotalChampionKills(int champ_kills) { this.totalChampionKills = champ_kills;}

    public void setAverageKDA(int avg_kda) { this.averageKDA = avg_kda;}

    public void setTotalDoubleKills(int double_kills) {this.totalDoubleKills = double_kills;}

    public void setTotalTripleKills(int triple_kills) {this.totalTripleKills = triple_kills;}

    public void setTotalQuadraKills(int quadra_kills) {this.totalQuadraKills = quadra_kills;}

    public void setTotalPentaKills(int penta_kills) {this.totalPentaKills = penta_kills;}

    public Profile(Parcel in)
    {
        String[] string_array = new String[3];
        int[] int_array = new int[10];
        in.readStringArray(string_array);
        this.name = string_array[0];
        this.previousRank = string_array[1];
        this.currentRank = string_array[2];
        this.rankedWins = int_array[0];
        this.normalWins = int_array[1];
        this.rankedLosses = int_array[2];
        this.normalLosses = int_array[3];
        this.totalChampionKills = int_array[4];
        this.averageKDA = int_array[5];
        this.totalDoubleKills = int_array[6];
        this.totalTripleKills = int_array[7];
        this.totalQuadraKills = int_array[8];
        this.totalPentaKills = int_array[9];
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {this.name, this.previousRank, this.currentRank});
        parcel.writeIntArray(new int[] {this.rankedWins, this.normalWins, this.rankedLosses, this.normalLosses
                            , this.totalChampionKills, this.averageKDA , this.totalDoubleKills, this.totalTripleKills
                            , this.totalQuadraKills, this.totalPentaKills});
    }

    public static final Creator CREATOR = new Creator(){
        public Profile createFromParcel(Parcel in){
            return new Profile(in);
        }
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
