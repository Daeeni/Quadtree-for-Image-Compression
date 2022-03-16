package ub6;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Node {
	BufferedImage image;
	Node[] childs;
	public Node(BufferedImage splitRaw ) {
		this.image = splitRaw;
		if(image.getWidth() != 1) {
			this.childs = this.getChilds();
		}
		this.image = this.compressed();
	}
	
	public Node[] getChilds() {
		BufferedImage[] childsRaws = this.split();
		Node[] childs = new Node[4];
		for(int i = 0; i < childs.length; i++) {
			childs[i] = new Node(childsRaws[i]);
		}
		return childs;
	}
	public BufferedImage[] split() {
	    BufferedImage imgs[] = new BufferedImage[4];
	    int dim = image.getWidth() / 2;
	    int cur = 0;
	    for (int i = 0; i < 2; i++) {
	        for (int j = 0; j < 2; j++) {
	            imgs[cur] = new BufferedImage(dim, dim, image.getType());
	            Graphics2D img_creator = imgs[cur].createGraphics();
                img_creator.drawImage(image, 0, 0, dim, dim, dim * j, dim * i, dim * j + dim, dim * i + dim, null);
                cur++;
	        }
	    }
	    return imgs;
	}
	public BufferedImage compressed() {
		BufferedImage imgComp = this.image;
		Graphics2D graphics = imgComp.createGraphics();
		graphics.setColor(averageColor(this.image));
		graphics.fillRect( 0, 0, imgComp.getWidth(), imgComp.getHeight() );
		graphics.dispose();
		return imgComp;
	}
	public static Color averageColor(BufferedImage img) {
	    long sumr = 0;
	    long sumg = 0;
	    long sumb = 0;
	    for (int x = 0; x < img.getWidth(); x++) {
	        for (int y = 0; y < img.getHeight(); y++) {
	            Color pixel = new Color(img.getRGB(x, y));
	            sumr += pixel.getRed();
	            sumg += pixel.getGreen();
	            sumb += pixel.getBlue();
	        }
	    }
	    int num = img.getHeight() * img.getWidth();
        float red = (float)(1.0/255)*(sumr / num);
        float green =(float)(1.0/255)*(sumg / num);
        float blue = (float)(1.0/255)*(sumb / num);
	    return new Color(red, green, blue);
	}
}
