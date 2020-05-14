package com.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;



import com.project.beans.Image;

public interface ImageRepo extends JpaRepository<Image,Long> {

}
