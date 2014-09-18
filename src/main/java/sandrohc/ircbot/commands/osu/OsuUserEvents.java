package sandrohc.ircbot.commands.osu;

public class OsuUserEvents {
	public String display_html;
	public int beatmap_id;
	public int beatmapset_id;
	public String date;
	public int epicfactor;

	@Override
	public String toString() {
		return "OsuUserEvents{" +
				"display_html='" + display_html + '\'' +
				", beatmap_id='" + beatmap_id + '\'' +
				", beatmapset_id='" + beatmapset_id + '\'' +
				", date='" + date + '\'' +
				", epicfactor='" + epicfactor + '\'' +
				'}';
	}
}
