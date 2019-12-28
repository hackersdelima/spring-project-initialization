package com.digi.app.transaction.controller;

import com.digi.app.component.CrudReturnService;
import com.digi.app.message.HttpResponses;
import com.digi.app.message.Messages;
import com.digi.app.telnet.component.TelnetConnectionComponent;
import com.digi.app.transaction.entities.TransactionEntity;
import com.digi.app.transaction.repo.TransactionRepository;
import com.digi.app.util.Utilities;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CrudReturnService crudReturnService;

    @Autowired
    private Utilities utilities;

    @GetMapping(value = "/create-form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView("transaction/transactionForm");
        modelAndView.addObject("pagetitle", "TRANSACTION");
        return modelAndView;
    }

    @GetMapping
    public ResponseEntity<?> transactionsJson() {
        List<TransactionEntity> list = transactionRepository.findAll();
        ResponseEntity<?> transactionEntities = crudReturnService.controllerReturnForList(list);
        return transactionEntities;
    }

    @GetMapping(path = "/currentUserDatas")
    public ResponseEntity<?> curUserTransactionsJson(Principal principal) {
        List<TransactionEntity> list = transactionRepository.findByInputter(principal.getName());
        ResponseEntity<?> transactionEntities = crudReturnService.controllerReturnForList(list);
        return transactionEntities;
    }

    @GetMapping(value = "/datatable")
    public ModelAndView datatable() {
        ModelAndView modelAndView = new ModelAndView("transaction/transactionDatatable");
        modelAndView.addObject("pagetitle", "TRANSACTION");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionEntity transactionEntity, Principal principal) {
        try {
            TransactionEntity savedTransactionEntity = null;
            String inputter = principal.getName();
            transactionEntity.setInputter(inputter);
            if (transactionEntity.getDigiTransactionId() == null || transactionEntity.getDigiTransactionId().isEmpty()) {
                String digiTransactionId = RandomStringUtils.randomAlphabetic(10);
                transactionEntity.setDigiTransactionId(digiTransactionId);
                savedTransactionEntity = transactionRepository.save(transactionEntity);
            } else {
                TransactionEntity updateableTransaction = transactionRepository.findById(transactionEntity.getDigiTransactionId()).get();
                if (updateableTransaction.getInputter().equals(principal.getName())) {
                    savedTransactionEntity = transactionRepository.save(transactionEntity);
                }
            }
            if (savedTransactionEntity != null) {
                return new ResponseEntity<Messages>(HttpResponses.created(savedTransactionEntity), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<Messages>(HttpResponses.badrequest(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/{digiTransactionId}")
    public ResponseEntity<?> getOneTransaction(@PathVariable String digiTransactionId) {
        try {
            TransactionEntity transactionEntity = transactionRepository.findById(digiTransactionId).get();
            if (transactionEntity != null) {
                return new ResponseEntity<Messages>(HttpResponses.fetched(transactionEntity), HttpStatus.OK);
            }
        } catch (Exception e) {
        }
        return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(path = "/{digiTransactionId}")
    public ResponseEntity<Messages> delete(@PathVariable String digiTransactionId, Principal principal, Authentication authentication) {
        TransactionEntity transactionEntity = transactionRepository.findById(digiTransactionId).get();
        if (transactionEntity.getInputter().equals(principal.getName()) || utilities.currentUserRoles(authentication).contains("AUTHORIZER")) {
            if (transactionEntity != null) {
                transactionRepository.delete(transactionEntity);
                return new ResponseEntity<Messages>(HttpResponses.received(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<Messages>(HttpResponses.badrequest(), HttpStatus.BAD_REQUEST);
        }
    }

    @Autowired
    TelnetConnectionComponent telnetConnectionComponent;

    @PostMapping(path = "/authorize/{digiTransactionId}")
    public ResponseEntity<Messages> authorizeTransaction(@PathVariable String digiTransactionId, Authentication authentication, Principal principal) {
        if (digiTransactionId != null && utilities.currentUserRoles(authentication).contains("AUTHORIZER")) {
            TransactionEntity transactionEntity = transactionRepository.findById(digiTransactionId).get();
            if (!transactionEntity.getInputter().equals(principal.getName())) {
                transactionEntity.setAuthorizer(principal.getName());
                TransactionEntity savedTransactionEntity = transactionRepository.save(transactionEntity);
                //TODO: call function to send query to telnet
                //String command="";
                // String telnetResponse=telnetConnection.telnetConnectionAndResponseString(command);
                //TODO: convert list to dto
                System.out.println("authorization called");
                if (savedTransactionEntity != null) {
                    return new ResponseEntity<Messages>(HttpResponses.received(), HttpStatus.ACCEPTED);
                }
            }
        }
        return new ResponseEntity<Messages>(HttpResponses.badrequest(), HttpStatus.BAD_REQUEST);
    }
}
