package banking.customers.infrastructure.hibernate.repository;

import org.springframework.stereotype.Repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import banking.common.infrastructure.hibernate.repository.AbstractDao;
import banking.common.util.PaginationHelper;
import banking.customers.domain.entity.Customer;
import banking.customers.domain.repository.CustomerDao;

@Repository("CustomerDao")
public class CustomerDaoImpl extends AbstractDao<Long, Customer> implements CustomerDao {

    private final PaginationHelper paginationHelper = new PaginationHelper();
    protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	public Customer create(Customer Customer) throws Exception {
		saveOrUpdate(Customer);
        return Customer;
	}

	@Override
	public Customer findById(Long id) throws Exception {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.idEq(id));
        criteria.add(Restrictions.eq("active", true));
        Customer customer = (Customer) criteria.uniqueResult();        
        return customer;
	}

}
