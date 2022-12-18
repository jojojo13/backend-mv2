package fa.group1.controller;



import fa.group1.dto.UserDTO;

import fa.group1.entities.User;


import fa.group1.services.UserDetailsService;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "api/user")
@CrossOrigin
public class UserController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        List<User> list = userDetailsService.getAllUser();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("user-account")
    public ResponseEntity<?> getUserAcount(@RequestParam Integer id) {
        User user = userDetailsService.findUserByID(id);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

//    @GetMapping("bookedTicket")
//    public ResponseEntity<?> getBookedTicket(@RequestParam Integer accountId) {
//        List<InvoiceDTO> invoiceDTO = invoiceRepository.getHis(accountId)
//                .stream()
//                .map(movie -> modelMapper.map(movie, InvoiceDTO.class))
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(invoiceDTO, HttpStatus.OK);
//    }

    @PutMapping("update")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        User u = userDetailsService.updateUser(userDTO);
        response.put("user", u);
        response.put("message", "updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("user-as-employee")
    public ResponseEntity<?> getEmployee(@RequestParam int index,
                                         @RequestParam int size) {
        Map<String, Object> response = new HashMap<>();

        Pageable paging = PageRequest.of(index, size);
        List<User> listUser = userDetailsService.getAllEmployeeList(paging);

        Page<User> pageEmployee = new PageImpl<>(listUser, paging, listUser.size());

        List<User> list = pageEmployee.getContent();
        List<UserDTO> userDTO = list
                .stream()
                .map(movie -> modelMapper.map(movie, UserDTO.class))
                .collect(Collectors.toList());
        response.put("employees", userDTO);
        response.put("currentPage", pageEmployee.getNumber());
        response.put("totalItem", pageEmployee.getTotalElements());
        response.put("totalPage", pageEmployee.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("add-employee")
    public ResponseEntity<?> getEmployee(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        User u = userDetailsService.addUserAsEmployee(user);
        response.put("user", u);
        response.put("message", "Add employee successfully!!!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
