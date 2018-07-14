package banking.customers.infrastructure.hibernate.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import banking.common.infrastructure.hibernate.repository.AbstractDao;
import banking.common.util.PaginationHelper;
import org.hibernate.transform.Transformers;
import java.util.Map;
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
	
	@Override
    public Map<String, Object> findAll(java.util.Optional<String> pag, java.util.Optional<String> pagLength) throws Exception {
		Criteria criteria = createEntityCriteria();
        
        criteria.setProjection(Projections.distinct(Projections.projectionList()
                .add(Projections.property("id"), "id")
        )).setResultTransformer(Transformers.aliasToBean(Customer.class));
		Map<String, Object> result = new HashMap<String, Object>();
	    Map<String, Object> map = paginationHelper.getPagination(criteria, "id", pag, pagLength);
	
	    List<Customer> listaCustomer = (List<Customer>) map.get("lista");
	    result.put("listaCustomer", listaCustomer);
	    result.put("totalPage", map.get("totalPage"));
	    result.put("totalResultCount", map.get("totalResultCount"));
	
	    return result;
	}

}
