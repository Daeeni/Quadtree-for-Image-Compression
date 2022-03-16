# Quadtree for Image Compression
Not a working application. These two files show an easy implemantation for image compression using quadtrees in Java.
Works only with square images. Uses a JFRame to print out the compressed image.

# How to use
    Change the path in the Quadtree.java file to a valid image.
    Change the JFRame size in the Quadtree.java file appropriately.

# How it works
At startup the image will be repeatedly divided to 4 equal subimages, using their average color. This will be done until each subimage is only 1 pixel in width and height. 
The result is a tree where each node contains 4 child nodes. 
A small algorithm will recusivly move through the tree and fill a BufferedImage with the necessary subimages. (specified by the user by the depth of the image)
