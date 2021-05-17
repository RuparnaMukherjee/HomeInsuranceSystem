package com.cg.hims.service;
import java.util.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hims.entities.Agent;
import com.cg.hims.entities.PolicyHolder;
import com.cg.hims.exceptions.AgentNotFoundException;
import com.cg.hims.exceptions.PolicyHolderNotFoundException;
import com.cg.hims.repository.IAgentRepository;
import com.cg.hims.repository.IPolicyHolderRepository;

/*
 * Author : RIDDHIMAN GHOSH ROY
 * Version : 1.0
 * Date : 18-05-2021
 * Description : This is PolicyHolder Service
*/

@Service
@Transactional
public class IPolicyHolderServiceImpl implements IPolicyHolderService {
	     
    @Autowired 
	IPolicyHolderRepository holderdao;
    @Autowired
    IAgentRepository agentDao;
    
    /************************************************************************************
	 * Method: addPolicyHolder
	 * Description: It is used to add PolicyHolders into PolicyHolder table
	 * @param PolicyHolder: PolicyHolder's reference variable.
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @Override: It is used to override the JpaRepository methods for performing CURD operations.
	 * Created By-RIDDHIMAN GHOSH ROY
     * Created Date -18-05-2021
	 * 
	 ************************************************************************************/
    
    @Override
    public PolicyHolder addPolicyHolder(PolicyHolder policyHolder)
    {
    	holderdao.save(policyHolder);
    	return policyHolder;
    }
    
    /************************************************************************************
	 * Method: updatePolicyHolder 
	 * Description: It is used to update PolicyHolders into PolicyHolder table
	 * @param PolicyHolder: PolicyHolder's reference variable.
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
	 * Created By-RIDDHIMAN GHOSH ROY
     *Created Date -18-05-2021
	 * 
	 ************************************************************************************/
    
    @Override
    public PolicyHolder updatePolicyHolder(PolicyHolder policyHolder) throws PolicyHolderNotFoundException
    {
    	if(!holderdao.existsById(policyHolder.getPolicyHolderId()))
    			throw new PolicyHolderNotFoundException("PolicyHolder not found");
    	
    	holderdao.save(policyHolder);
    	return policyHolder;
    }
    
    /************************************************************************************
	 * Method: findPolicyHolderByEmailId 
	 * Description: It is used to view PolicyHolder by EmailId from PolicyHolder table
	 * @param PolicyHolder: String EmailId
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
	 * Created By-RIDDHIMAN GHOSH ROY
     * Created Date -18-05-2021 
	 * 
	 ************************************************************************************/
    
    @Override
    public Optional<PolicyHolder> findPolicyHolderByEmailId(String email) throws PolicyHolderNotFoundException
    {
    	Optional<PolicyHolder> policyHolder=holderdao.findByEmailId(email);
    	if(policyHolder==null)
    		throw new PolicyHolderNotFoundException("PolicyHolder not found");
    	return policyHolder;
    }
    
    /************************************************************************************
	 * Method: findPolicyHolderById
	 * Description: It is used to view PolicyHolders by id from PolicyHolder table
	 * @param PolicyHolder: int id
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
	 * Created By-RIDDHIMAN GHOSH ROY
     * Created Date -18-05-2021 
	 * 
	 ************************************************************************************/
    
    @Override
    public Optional<PolicyHolder> findPolicyHolderById(int id) throws PolicyHolderNotFoundException
    {
    	if(!holderdao.existsById(id))
    		throw new PolicyHolderNotFoundException("PolicyHolder not found");
    	return holderdao.findById(id);
    }
    
    /************************************************************************************
	 * Method: removePolicyHolder 
	 * Description: It is used to remove PolicyHolder from PolicyHolders table
	 * @param PolicyHolder: int PolicyHolderid
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
	 * Created By-RIDDHIMAN GHOSH ROY
     * Created Date 18-05-2021 
	 * 
	 ************************************************************************************/
    
    @Override
    public String removePolicyHolder(int id) throws PolicyHolderNotFoundException
    {
    	if(!holderdao.existsById(id))
			throw new PolicyHolderNotFoundException("quote not found");
		holderdao.deleteById(id);
		return "Quote deleted successfully";
    }
    
    /************************************************************************************
	 * Method: viewAllPolicyHolder 
	 * Description: It is used to view all PolicyHolders in PolicyHolder table
	 * @returns PolicyHolder It returns PolicyHolder with details
	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
	 * Created By-RIDDHIMAN GHOSH ROY
     * Created Date -18-05-2021
	 * 
	 ************************************************************************************/
	
    @Override
    public List<PolicyHolder> showAllPolicyHolders()
    {
    	List<PolicyHolder> h_list=holderdao.findAll();
    	return h_list;
    }
    /************************************************************************************
   	 * Method: viewAllPolicyHolderByAgentId 
   	 * Description: It is used to view all PolicyHolders in PolicyHolder table by agent id
   	 * @returns PolicyHolder It returns PolicyHolder with details
   	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
   	 * Created By-RIDDHIMAN GHOSH ROY
        * Created Date -18-05-2021
   	 * 
   	 ************************************************************************************/
    public List<PolicyHolder> viewPolicyHolderByAgentId(int id) throws AgentNotFoundException {
		// TODO Auto-generated method stub
    	if(!agentDao.existsById(id))
    		throw new AgentNotFoundException("agent not found");
    	Optional<Agent> agent=agentDao.findById(id);
		return holderdao.findAllByAgent(agent);
	}
    
    /************************************************************************************
   	 * Method: viewIdlePolicyHolder 
   	 * Description: It is used to view all PolicyHolders in PolicyHolder table without agent
   	 * @returns PolicyHolder It returns PolicyHolder with details
   	 * @Override:  It is used to override the JpaRepository methods for performing CURD operations.
   	 * Created By-RIDDHIMAN GHOSH ROY
        * Created Date -18-05-2021
   	 * 
   	 ************************************************************************************/
    
    public List<PolicyHolder> viewIdlePolicyHolder(){
    	return holderdao.viewIdlePolicyHolder();
    }
}
