package yunha.config;

import yunha.annotation.Bean;
import yunha.bean.TestBean;

public class TestBeanConfig {

    @Bean
    public TestBean testBean() {
        TestBean testBean = new TestBean();
        testBean.setName("콩");
        return testBean;
    }

    @Bean(value = "bean2")
    public TestBean redBean() {
        TestBean testBean = new TestBean();
        testBean.setName("콩");
        return testBean;
    }
}
