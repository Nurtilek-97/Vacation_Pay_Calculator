package ru.neoflex.vacation_pay_calculator.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.vacation_pay_calculator.service.days.CalculationOfDaysService;
import ru.neoflex.vacation_pay_calculator.service.vacation.VacationPayCalculatorService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
public class
VacationPayCalculatorController {

    private final VacationPayCalculatorService vacationPayCalculatorService;
    private final CalculationOfDaysService calculationOfDaysService;

    public VacationPayCalculatorController(VacationPayCalculatorService vacationPayCalculatorService,
                                           CalculationOfDaysService calculationOfDaysService) {
        this.vacationPayCalculatorService = vacationPayCalculatorService;
        this.calculationOfDaysService = calculationOfDaysService;
    }

    @GetMapping("/calculacte")
    public BigDecimal getVacationPay(
            @RequestParam("averageSalary") BigDecimal averageSalaryPerYear,
            @RequestParam("vacationDays") int vacationDays,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startVacationDate
    ) {
        if (startVacationDate.isPresent()) {
            vacationDays = calculationOfDaysService.calculatePaidDays(vacationDays, startVacationDate.get());
        }
        return vacationPayCalculatorService.getVacationPayCalculation(averageSalaryPerYear, vacationDays);

    }
}