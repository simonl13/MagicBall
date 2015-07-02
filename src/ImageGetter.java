import javax.swing.ImageIcon;


public class ImageGetter {
	
	String imageName;
	
	public ImageIcon getImage(String imageName) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/"+ imageName));
		return ii;
		
	}
}
