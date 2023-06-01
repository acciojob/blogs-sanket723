package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog = blogRepository2.findById(blogId).get();

        image.setBlog(blog);

        blog.getImageList().add(image);

        blogRepository2.save(blog);

        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image = imageRepository2.findById(id).get();

        String imageDimensions = image.getDimensions();
        //image dimension is in String format ex: 2X2
        //we have to convert it into integer like 2*2=4
        //below is the process to calculate total image dimension

        int indexOfX = imageDimensions.indexOf('X');

        String x = imageDimensions.substring(0,indexOfX);
        String y = imageDimensions.substring(indexOfX+1);

        int totalImageDimension = Integer.parseInt(x) * Integer.parseInt(y);


        //Like above, Similarly find total screen dimension in integer format

        int screenIndexOfX = screenDimensions.indexOf('X');

        String screenX = screenDimensions.substring(0,screenIndexOfX);
        String screenY = screenDimensions.substring(screenIndexOfX+1);

        int totalScreenDimension = Integer.parseInt(screenX) * Integer.parseInt(screenY);


       // int count = totalScreenDimension/totalImageDimension;

        //Final count
        int count = (Integer.parseInt(screenX)/Integer.parseInt(x)) * (Integer.parseInt(screenY)/Integer.parseInt(y));

        return count;

    }
}
