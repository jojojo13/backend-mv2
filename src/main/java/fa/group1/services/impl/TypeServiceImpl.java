package fa.group1.services.impl;

import fa.group1.entities.Type;
import fa.group1.exceptions.ResourceNotFoundException;
import fa.group1.repository.TypeRepository;
import fa.group1.services.TypeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeRepository typeRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeServiceImpl.class);
    
    @Override
    public List<Type> getAllType() {
       List<Type> types= typeRepository.findAll();
       if(types.isEmpty()){
    	   LOGGER.error("Not found any types");
           throw new ResourceNotFoundException("Not found any types");
       }
       return types;
    }
}
