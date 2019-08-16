package com.system.usermanager.controller;

import com.system.usermanager.model.User;
import com.system.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserManagerController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model
    ) {
        return "/greeting";
    }

    @GetMapping("/login")
    public String login(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "/login";
    }

    @GetMapping("/students")
    public String students(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();

        model.put("users", users);

        return "/users";
    }

   /* @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }


    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);

        return "/main";
    }

    @PostMapping("text")
    public String text(@RequestParam String text, Map<String, Object> model) {
        Iterable<Message> messages;

        if (text != null && !text.isEmpty()) {
            messages = messageRepo.findByText(text);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);

        return "/main";
    }


    @PostMapping("peoples")
    public String peoples(@RequestParam String studentName, @RequestParam String studentDiscipline, @RequestParam String studentExam, @RequestParam String studentSpecialty,
                          @RequestParam String studentExamList, @RequestParam String studentDocument, @RequestParam String studentEducation,
                          @RequestParam String studentAvarageMark, Map<String, Object> model) {

        tablesControllerImpl.addInTables(studentName, studentDiscipline,studentExam,studentSpecialty, studentExamList,studentDocument,
                studentEducation, studentAvarageMark);

        Student student=new Student(studentName,studentDiscipline,studentExam,studentSpecialty,studentExamList,studentDocument,studentEducation,studentAvarageMark);

        studentRepository.save(student);

        Iterable<Student> students = studentRepository.findAll();


        model.put("students", students);

        return "redirect:/students";
    }

    @PostMapping("byname")
    public String byname(@RequestParam String name, Map<String, Object> model) {
        Iterable<Student> students;

        if (name != null && !name.isEmpty()) {
            students = studentRepository.findByStudentNameOrderByStudentName(name);
        } else {
            students = studentRepository.findAll();
        }

        model.put("students", students);

        return "/students";
    }

    @PostMapping("delete")
    public String delete(@RequestParam String name, Map<String, Object> model) {
        Iterable<Student> students;

        if (name != null && !name.isEmpty())
            studentRepository.removeAllByStudentName(name);

        students = studentRepository.findAll();


        model.put("students", students);

        return "redirect:/students";
    }*/
}
