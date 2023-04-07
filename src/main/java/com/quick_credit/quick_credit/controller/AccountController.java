package com.quick_credit.quick_credit.controller;
import com.quick_credit.quick_credit.entity.Account;
import com.quick_credit.quick_credit.entity.Deposit;
import com.quick_credit.quick_credit.entity.User;
import com.quick_credit.quick_credit.entity.Withdraw;
import com.quick_credit.quick_credit.repository.AccountRepository;
import com.quick_credit.quick_credit.repository.DepositRepository;
import com.quick_credit.quick_credit.repository.UserRepository;
import com.quick_credit.quick_credit.service.AccountService;
import com.quick_credit.quick_credit.service.DepositService;
import com.quick_credit.quick_credit.service.UserService;
//import com.quick_credit.quick_credit.util.FileUploadUtil;
import com.quick_credit.quick_credit.service.WithdrawService;
import com.quick_credit.quick_credit.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Transactional
@Controller
public class AccountController {
    @Autowired
    private DepositRepository depositRepository;

    private UserService userService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private WithdrawService withdrawService;
    private AccountService accountService;

    private UserRepository userRepository;

    private AccountRepository accountRepository;
    public AccountController(AccountService accountService, AccountRepository accountRepository, UserRepository userRepository){
        this.accountService = accountService;
        this.accountRepository=accountRepository;
        this.userRepository=userRepository;
    }

    @GetMapping("/account/savings")
    public String saving(Principal principal , ModelMap modelMap){
        String loggedUser = principal.getName();
        User user = userRepository.findByEmail(loggedUser);

        Account myaccount = accountRepository.findByUser(user);


        if(myaccount == null){
            Account account = new Account();
            modelMap.addAttribute("account",account);
        }
        modelMap.addAttribute("user",user);
        modelMap.addAttribute("myaccount",myaccount);

        return "clients/savings_template";
    }
    @PostMapping("/account/storeSavings")
    public String storeAccount(@ModelAttribute("account") Account account, Principal principal){
        String loggedUser = principal.getName();
        User user = userRepository.findByEmail(loggedUser);
        Instant instant = Instant.now();
        ZoneId ztime = ZoneId.of( "Africa/Lusaka" );
        System.out.println("TIME");
        System.out.println(ztime);

        Date time = new Date();
        account.setUser(user);
        account.setBalance(0.0);
        account.setBookBalance(0.0);
        account.setStatus("active");
        account.setInterest(0.05);
        account.setInterestAccum(0.0);
        account.setLocked(0);
        account.setInterest_at(time);
        accountService.saveAccount(account);
        return "redirect:/account/savings";
    }

    @PostMapping("/account/savings/deposit")
    public String storeDeposit(
//            @RequestParam("depositVerification") MultipartFile multipartFile,
            @ModelAttribute("deposit")Deposit deposit,
            Principal principal) throws IOException {

//            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            deposit.setDepositVerification(fileName);
            String loggedUser = principal.getName();
            User user = userRepository.findByEmail(loggedUser);

            deposit.setUser(user);
            deposit.setDeposit_status("pending");
            Date time = new Date();
            deposit.setCreated_at(time);
            depositService.saveDeposit(deposit);

            Account myaccount = accountRepository.findByUser(user);
            if(deposit.getDeposit() < 0) {
               throw new IllegalArgumentException("You can not deposit amount less than K0");
            }
            else {
                myaccount.setBalance(myaccount.getBalance() + deposit.getDeposit());
                accountService.updateAccount(myaccount);
            }

//
//
//            String uploadDir = "user-photos/"+ user.getId();
//
//            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/account/savings";
    }
    @PostMapping("/account/savings/withdraw")
    public String storeWithdraw(
            @ModelAttribute("withdraw") Withdraw withdraw,
            Principal principal,
            RedirectAttributes redirectAttributes) throws IOException {

        String loggedUser = principal.getName();
        User user = userRepository.findByEmail(loggedUser);
        Account myaccount = accountRepository.findByUser(user);


        if(withdraw.getWithdraw() < (myaccount.getBalance() - 100)) {
            Date time = new Date();
            withdraw.setCreated_at(time);
            withdraw.setWithdraw_status("pending");
            withdraw.setUser(user);
            withdrawService.saveWithdraw(withdraw);

            myaccount.setBalance(myaccount.getBalance()-withdraw.getWithdraw());
            accountService.updateAccount(myaccount);
            redirectAttributes.addFlashAttribute("success", "You have successfully withdrawn "+ withdraw.getWithdraw() +"from your account.");
            return "redirect:/account/savings";

        }
        else{
            redirectAttributes.addFlashAttribute("error", "Sorry, You have insufficient balance !");
            return "redirect:/account/savings";
        }


    }

    @GetMapping("/account/withdraws")
    public String withdraws(Principal principal, ModelMap modelMap){
        String loggedUser = principal.getName();
        User user = userRepository.findByEmail(loggedUser);

        List<Withdraw> withdraws = withdrawService.getAllWithdraws(user);
        modelMap.addAttribute("user",user);
        modelMap.addAttribute("withdraws", withdraws);
        return "clients/withdraws";
    }

    @GetMapping("/account/deposits")
    public String deposits(Principal principal, ModelMap modelMap){
        String loggedUser = principal.getName();
        User user = userRepository.findByEmail(loggedUser);

        List<Deposit> deposits = depositRepository.findDeposits(user);
        modelMap.addAttribute("user",user);
        modelMap.addAttribute("deposits", deposits);
        return "clients/deposits";
    }


}
