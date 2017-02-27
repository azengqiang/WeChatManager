package pre.my.test.robot.dto.message;

/**
 * 回复图片消息
 */
public class ImageMessage extends Message{
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}
