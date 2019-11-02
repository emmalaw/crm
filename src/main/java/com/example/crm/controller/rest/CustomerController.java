package com.example.crm.controller.rest;

import java.util.List;

import com.example.crm.domain.dto.in.CustomerSearchInput;
import com.example.crm.domain.dto.in.CustomerStatusInput;
import com.example.crm.domain.dto.in.NoteInput;
import com.example.crm.domain.dto.out.Customer;
import com.example.crm.domain.dto.out.CustomerNote;
import com.example.crm.service.CustomerNoteService;
import com.example.crm.service.CustomerService;
import com.example.crm.util.exception.CustomerNotFoundException;
import com.example.crm.util.exception.CustomerNoteNotFoundException;
import com.example.crm.util.exception.InvalidCustomerStatusException;
import com.example.crm.util.exception.VersionNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * CustomerController
 */
@Api(value = "Customer Relation Management System")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerNoteService customerNoteService;

    //
    // Customer
    //
    /**
     * 
     * @return ResponseEntity<Customer> customer object according to id
     * @throws CustomerNotFoundException
     */
    @ApiOperation(value = "Find  customer by  id", response = Customer.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById (
            @ApiParam(value = "Customer id from which customer object will retrieve", required = true) 
            @PathVariable Long id)
            throws CustomerNotFoundException {
        Customer customer = customerService.findByCustomerId(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    /**
     * @return ResponseEntity<List<Customer>> all customers
     * @throws CustomerNoteNotFoundException
     */
    @ApiOperation(value = "Find customer with condition (nameFirst, nameLast, nameMiddle, phone) and sorting order", response = Customer.class, responseContainer = "List")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<List<Customer>> getCustomerList(
            @ApiParam(value = "search string") @RequestBody CustomerSearchInput searchInput)
            throws CustomerNotFoundException {
        return new ResponseEntity<List<Customer>>(customerService.getCustomerList(searchInput), HttpStatus.OK);
    }

    /**
     * 
     * @param statusInput
     * @return
     * @throws CustomerNotFoundException
     * @throws VersionNotFoundException
     * @throws InvalidCustomerStatusException
     */
    @ApiOperation(value = "update customer statue", response = Boolean.class)
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public ResponseEntity<Boolean> updateStatus(
            @ApiParam(value = "Status input object", required = true) @RequestBody CustomerStatusInput statusInput)
            throws CustomerNotFoundException, VersionNotFoundException, InvalidCustomerStatusException {
        Boolean ok = customerService.saveStatus(statusInput);
        return new ResponseEntity<Boolean>(ok, HttpStatus.OK);
    }


    //
    //   Note
    //
     /**
     * 
     * @param id
     * @return
     * @throws CustomerNoteNotFoundException
     */
    @ApiOperation(value = "Retrieve note by id", response = Boolean.class)
    @RequestMapping(value = "/note/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerNote> getNoteByCustomerNoteId(
        @ApiParam(value = "Note id from which note object will retrieve", required = true)
        @PathVariable Long id) throws CustomerNoteNotFoundException {
        CustomerNote customerNote = customerNoteService.findById(id);
        return new ResponseEntity<CustomerNote>(customerNote, HttpStatus.OK);
    }

    /**
     * 
     * @param NoteInput
     * @return
     * @throws CustomerNotFoundException
     * @throws CustomerNoteNotFoundException
     */
    @ApiOperation(value = "Add note", response = Boolean.class)
    @RequestMapping(value = "/note/add", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addNote(@ApiParam(value = "Note", required = true) @RequestBody NoteInput note)
            throws CustomerNotFoundException, CustomerNoteNotFoundException {
        customerNoteService.add(note);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
    }

    /**
     * 
     * @param NoteInput
     * @return ResponseEntity<Boolean> HttpStatus = <code>201</code> and <code>true</code>when update successfully</br>
     * @throws CustomerNotFoundException
     * @throws CustomerNoteNotFoundException
     * @throws VersionNotFoundException
     * @see NoteInput
     */
    @ApiOperation(value = "Edit note", response = Boolean.class)
    @RequestMapping(value = "/note/edit", method = RequestMethod.POST)
    public ResponseEntity<Boolean> editNote(
        @ApiParam(value = "Note", required = true) @RequestBody NoteInput note) 
        throws CustomerNotFoundException, CustomerNoteNotFoundException, VersionNotFoundException {
        customerNoteService.save(note);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete note", response = Boolean.class)
    @RequestMapping(value = "/note/delete", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteNote(
        @ApiParam(value = "Delete note", required = true) @RequestBody NoteInput note) 
        throws CustomerNotFoundException, CustomerNoteNotFoundException, VersionNotFoundException {
        customerNoteService.delete(note);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
}
