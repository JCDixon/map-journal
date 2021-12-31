/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.mapjournal.dao;

import com.mthree.mapjournal.dto.Pin;
import com.mthree.mapjournal.dto.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author john
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
    //List<Tag> findByPin(Pin pin);
}
