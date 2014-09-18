package sandrohc.ircbot.commands.osu;

import java.util.Arrays;

public class OsuUser {
	public String user_id;
	public String username;
	public int playcount;
	public float level;
	public double accuracy;
	public String pp_rank;
	public float pp_raw;
	public long ranked_score;
	public long total_score;
	public int count_rank_ss;
	public int count_rank_s;
	public int count_rank_a;
	public int count300;
	public int count100;
	public int count50;
	public String country;
	public OsuUserEvents[] events;

	@Override
	public String toString() {
		return "OsuUser{" +
				"user_id='" + user_id + '\'' +
				", username='" + username + '\'' +
				", playcount='" + playcount + '\'' +
				", level='" + level + '\'' +
				", accuracy='" + accuracy + '\'' +
				", pp_rank='" + pp_rank + '\'' +
				", pp_raw='" + pp_raw + '\'' +
				", ranked_score='" + ranked_score + '\'' +
				", total_score='" + total_score + '\'' +
				", count_rank_ss='" + count_rank_ss + '\'' +
				", count_rank_s='" + count_rank_s + '\'' +
				", count_rank_a='" + count_rank_a + '\'' +
				", count300='" + count300 + '\'' +
				", count100='" + count100 + '\'' +
				", count50='" + count50 + '\'' +
				", country='" + country + '\'' +
				", events=" + Arrays.toString(events) +
				'}';
	}
}
