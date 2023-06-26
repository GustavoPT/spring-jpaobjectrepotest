package t;

import com.userfront.models.MyObject;
import com.userfront.repositories.MyObjectRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class MyObjectRepositoryTest
{
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private MyObjectRepository myObjectRepository;
//
//    @Test
//    public void testSaveMyObjectWithRelatedObject() {
//        // Arrange
//        MyObject myObject1 = new MyObject();
//        myObject1.setName("My Object 1");
//
//        MyObject myObject2 = new MyObject();
//        myObject2.setName("My Object 2");
//
//        RelatedObject relatedObject = new RelatedObject();
//        relatedObject.setName("Related Object");
//
//        myObject1.addRelatedObject(relatedObject);
//        myObject2.addRelatedObject(relatedObject);
//
//        // Act
//        MyObject savedObject1 = myObjectRepository.save(myObject1);
//        MyObject savedObject2 = myObjectRepository.save(myObject2);
//
//        // Assert
//        Assertions.assertNotNull(savedObject1.getId());
//        Assertions.assertNotNull(savedObject2.getId());
//        Assertions.assertEquals(1, savedObject1.getRelatedObjects().size());
//        Assertions.assertEquals(1, savedObject2.getRelatedObjects().size());
//        Assertions.assertEquals(2, relatedObject.getMyObjects().size());
//    }
//
//    // Add more test cases as needed
}
//
