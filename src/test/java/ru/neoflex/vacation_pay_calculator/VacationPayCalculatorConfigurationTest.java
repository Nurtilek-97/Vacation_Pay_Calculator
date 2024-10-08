package ru.neoflex.vacation_pay_calculator;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = VacationPayCalculatorApplication.class)
@AutoConfigureMockMvc
public abstract class VacationPayCalculatorConfigurationTest {
}
