function remaininBalanceCal() {

    var totalAmount = document.getElementById("total_account_amount").value;
    var withdrawAmounts = document.getElementById("withdraw").value;
    var remainingBalance = parseFloat(totalAmount - withdrawAmounts);

    if(remainingBalance < 0){
        document.getElementById("remaining_balnace").value = "Maximum amount is K"+ totalAmount;
    }
    else{
      document.getElementById("remaining_balnace").value = remainingBalance;
    }

}