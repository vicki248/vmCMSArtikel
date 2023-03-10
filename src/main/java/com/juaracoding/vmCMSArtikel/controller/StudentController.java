package com.juaracoding.vmCMSArtikel.controller;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 18:12
@Last Modified 10/03/2023 18:12
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.configuration.OtherConfig;
import com.juaracoding.vmCMSArtikel.model.Student;
import com.juaracoding.vmCMSArtikel.service.StudentService;
import com.juaracoding.vmCMSArtikel.utils.MappingAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/school")
public class StudentController {
    private StudentService studentService;

    private MappingAttribute mappingAttribute = new MappingAttribute();
    private Map<String,Object> objectMapper = new HashMap<String,Object>();

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/v1/students")
    public String listStudents(Model model, WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

//	@GetMapping("/home")
//	public String listStudentsHome(Model model, WebRequest request) {
//		if(OtherConfig.getFlagSessionValidation().equals("y"))
//		{
//			mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//			if(request.getAttribute("USR_ID",1)==null){
//				return "redirect:/api/check/logout";
//			}
//		}
//		model.addAttribute("students", studentService.getAllStudents());
//		return "home";
//	}

    @GetMapping("/v1/students/new")
    public String createStudentForm(Model model,WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        // create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";

    }

    @PostMapping("/v1/students")
    public String saveStudent(@ModelAttribute("student") Student student, Model model, WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        studentService.saveStudent(student);
        return "redirect:/api/school/v1/students";
    }

    @GetMapping("/v1/students/edit/{id}")
    public String editStudentForm(@PathVariable("id") Long Id, Model model, WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        model.addAttribute("student", studentService.getStudentById(Id));
        return "edit_student";
    }

    @PostMapping("/v1/students/{id}")
    public String updateStudent(@PathVariable("id") Long id,
                                @ModelAttribute("student") Student student,
                                Model model, WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        // get student from database by id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        // save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/api/school/v1/students";
    }

    // handler method to handle delete student request

    @GetMapping("/v1/students/{id}")
    public String deleteStudent(@PathVariable Long id, Model model, WebRequest request) {
        if(OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
