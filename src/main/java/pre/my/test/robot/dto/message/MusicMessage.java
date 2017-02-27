package pre.my.test.robot.dto.message;

/**
 * 回复音乐消息
 */
public class MusicMessage extends Message{
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
