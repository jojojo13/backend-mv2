package fa.group1.services;

import fa.group1.entities.Type;
import fa.group1.entities.User;
import fa.group1.repository.TypeRepository;
import fa.group1.services.impl.TypeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class TypeServiceTest {
    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeServiceImpl typeService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testGetAllType() {
        List<Type> typeList = new ArrayList<Type>();
        typeList.add(new Type(1, "Hanh dong", null));
        typeList.add(new Type(2, "Vien tuong", null));
        typeList.add(new Type(3, "Hanh dong", null));
        when(typeRepository.findAll()).thenReturn(typeList);
        List<Type> result = typeService.getAllType();
        assertEquals(3, result.size());
    }
    @Test
    public void testGetAllTypeNull() {
        List<Type> typeList = new ArrayList<Type>();
        try{
            when(typeRepository.findAll()).thenReturn(typeList);
            List<Type> result = typeService.getAllType();
        }catch (Exception e){
            final String msg = "Not found any types";
            Assertions.assertEquals(msg, e.getMessage());
        }
    }
}
