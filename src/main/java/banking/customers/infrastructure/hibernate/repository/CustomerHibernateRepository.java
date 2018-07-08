package banking.customers.infrastructure.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import banking.accounts.domain.entity.Customer;
import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import banking.customers.domain.repository.CustomerRepository;

@Repository
public class CustomerHibernateRepository extends BaseHibernateRepository<Customer> implements CustomerRepository {

	@Override
	public Customer findById(long id) throws Exception {
		Criteria criteria = getSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("id", id));
		return (Customer) criteria.uniqueResult();
	}

	@Override
	public Customer findByName(String firstName, String lastName) throws Exception {
		Criteria criteria = getSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("firstName", firstName));
		criteria.add(Restrictions.eq("lastName", lastName));
		return (Customer) criteria.uniqueResult();
	}
	
	@Override
	public List<Customer> findAll(String pag, String pagLenth) throws Exception {
		Criteria criteria = getSession().createCriteria(Customer.class);
		criteria.add(Restrictions.eq("pag", pag));
		criteria.add(Restrictions.eq("pagLenth", pagLenth));
		return (List<Customer>) criteria.list();
	}
	
	@Autowired
	public void save(Customer customer) {
		super.save(customer);
	}
	
	public void saveOrUpdate(Customer customer) {
		super.saveOrUpdate(customer);
	}
	
}