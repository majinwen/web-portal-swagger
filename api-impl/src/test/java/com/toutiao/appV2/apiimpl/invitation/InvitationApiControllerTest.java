package com.toutiao.appV2.apiimpl.invitation;

import com.toutiao.app.domain.invitation.InvitationCodeDo;
import com.toutiao.app.service.invitation.impl.InvitationCodeServiceImpl;
import com.toutiao.appV2.model.invitation.InvitationRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author : zym
 * @date : 2018/11/21 18:55
 * @desc :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class InvitationApiControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @InjectMocks
    private InvitationApiController invitationApiController;

    @Mock
    private InvitationCodeServiceImpl invitationCodeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invitationApiController).build();  //构造MockMvc
    }

    /**
     * @see InvitationApiController#getInvitation(InvitationRequest)
     */
    @Test
    public void getInvitation() throws Exception {
        Mockito.when(invitationCodeService.getInvitation(Mockito.any())).thenReturn(
                new InvitationCodeDo()
                        .setId(1)
                        .setCode(77777777)
        );

        this.mockMvc
                .perform(get("/rest/invitation/getInvitation"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.code", is(77777777)));
    }

}