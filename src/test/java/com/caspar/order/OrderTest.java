package com.caspar.order;

import com.caspar.order.dto.OrderDTO;
import com.caspar.order.entity.OrderDetail;
import com.caspar.order.service.OrderMasterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {

    private final String BUYER_OPENID = "Jun1chi";

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void testCreate() {
        for (int i = 0; i < 30; i++) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setBuyerName("Caspar");
            orderDTO.setBuyerPhone("17306672805");
            orderDTO.setBuyerOpenid(BUYER_OPENID);
            orderDTO.setBuyerAddress("123");

            List<OrderDetail> orderDetailList = Lists.newArrayList();
            OrderDetail o1 = new OrderDetail();
            o1.setProductId("1");
            o1.setProductQuantity(1);

            OrderDetail o2 = new OrderDetail();
            o2.setProductId("5");
            o2.setProductQuantity(1);

            orderDetailList.add(o1);
            orderDetailList.add(o2);

            orderDTO.setOrderDetailList(orderDetailList);

            orderMasterService.create(orderDTO);
        }
    }

    @Test
    public void testGetList() {
        PageHelper.startPage(0, 10);
        List<OrderDTO> orderDTOList = orderMasterService.selectByBuyerOpenId(BUYER_OPENID);
        PageInfo<OrderDTO> pageInfo = new PageInfo<>(orderDTOList, 5);
        System.out.println(pageInfo);
    }

}
