import Utils.PersistUtils;
import com.github.javafaker.Faker;
import hopital.Medecin;
import hopital.Service;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.util.Locale;
import java.util.Random;

public class Main {
    private static final SessionFactory ourSessionFactory;

    private static boolean fiftyPercent() {
        final Random random = new Random();
        return random.nextBoolean();
    }

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate/hibernate.cfg.xml");

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        PersistUtils.setEntityManager(session.getEntityManagerFactory().createEntityManager());
        try {
            Faker faker = new Faker(new Locale("fr"));

            for (int i = 0; i < 5; i++) {
                Service service = new Service(faker.gameOfThrones().city(), faker.address().fullAddress());
                Medecin chef = null;
                PersistUtils.persistEntity(service);
                for (int j = 0; j < 3; j++) {
                    Medecin medecin = new Medecin(
                            faker.name().lastName(),
                            faker.name().firstName(),
                            faker.number().randomDouble(2, 1000, 2000));
                    medecin.setChef(null);
                    medecin.setService(service);
                    PersistUtils.persistEntity(medecin);
                    service.getMedecinList().add(medecin);
                    if (j == 0) {
                        medecin.getServiceList().add(service);
                        chef = medecin;
                    }
                }
                service.setChef(chef);
                PersistUtils.persistEntity(service);
            }


            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o.toString());
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            session.close();
        }
    }
}
