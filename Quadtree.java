import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Quadtree {
	public static void main(String[] args) {
		try {
			Node node = createTree("ENTER PATH TO IMAGE HERE");
//			Print single Layer
			showLayer(node, 10);
			
//			Print all Layers
			for(int i = 0; i <= 10; i++) {
//				showLayer(toTest, i);
				Thread.sleep(200);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static Node createTree(String filePath) throws IOException {
		File file = new File(filePath);
		BufferedImage image = ImageIO.read(file);
		Node root = new Node(image);
		return root;
	}
	public static BufferedImage getImageRek(int w, int h, int layer, Node cur) {
		int dim = cur.image.getHeight();
		if(layer == 0) return cur.image;
		if(w < dim/2) {
			if(h < dim/2) {
				if(layer == 1) {
					return cur.childs[0].image;
				}else {
					return  getImageRek( w,  h,  layer-1, cur.childs[0]);
				}
			}else {
				if(layer == 1) {
					return cur.childs[2].image;
				}else {
					return getImageRek( w,  h-(dim/2),  layer-1, cur.childs[2]);
				}
			}
		}else {
			if(h < dim/2) {
				if(layer == 1) {
					return cur.childs[1].image;
				}else {
					return getImageRek( w-(dim/2),  h,  layer-1, cur.childs[1]);
				}
			}else {
				if(layer == 1) {
					return cur.childs[3].image;
				}else {
					return getImageRek( w-(dim/2),  h-(dim/2),  layer-1, cur.childs[3]);
				}
			}
		}
	}
	public static void showLayer(Node root, int layer) throws IOException {
			int checker = (int)(Math.log10(root.image.getHeight()) / Math.log10(2));
			if(layer > checker) {
				System.out.println("The selected layer tries to access a depth that is less than 1 pixel. Please select a higher layer."
						+ "\nThe lowest possible layer is: "+checker);
				return;
			}
			int dim = root.image.getHeight() / (int)(Math.pow(2, layer));
			BufferedImage result = root.image;
			Graphics2D g = result.createGraphics();
			int height = 0;
			for(int width = 0; width < root.image.getHeight(); width = width+dim) {
				for(; height < root.image.getHeight(); height = height + dim) {
					BufferedImage cur = getImageRek(width, height, layer, root);
					g.drawImage(cur, width, height, width+dim, height+dim, 0, 0, dim, dim, null);	
				}
				height = 0;
			}
			printImg(result);
		    g.dispose();
	}
	public static void printImg(BufferedImage image) {
		ImageIcon imageIcon = new ImageIcon(image);
		JFrame jf = new JFrame();
		jf.setLayout(new FlowLayout());
		jf.setSize(1024, 1024);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        jf.add(jLabel);
        jf.setVisible(true);
        jf.pack();
	}


}
