package hbi.core.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class SpringAnnocation {
    @Autowired
    String string;
}
