package ca.ulaval.glo3004.tp2;

public class ControlleurValve {

	
	public Object lock = new Object();
	
	
	private int _n;
	
	
	private int _na = 0;
	private int _nb = 0;
	private int _wa = 0;
	private int _wb = 0;
	
	private int _t = 1;
	
	private String _type_initial = "a";
	private String _type_courant_ouvre_valve = _type_initial;
	private String _type_courant_ferme_valve = "a";
	private int _index_initial = 1;

	private int _index_courant_ouvre_valve = _index_initial;
	private int _index_courant_ferme_valve = 1;
   
    
	
	public ControlleurValve(int n) {
		_n = n;
		
	}
	
	
	public boolean requeteValve(int index, String type) {
		
		synchronized(lock) {
			
			if (type == "a" && _wa < _n) {
				_wa += 1;
				return true;
			}
			if (type == "b" && _wb < _n) {
				_wb += 1;
				return true;
			}
			
			return false;
		}
		
	}
	
	public boolean ouvreValve(int index, String type) 
{
		
		synchronized(lock) 
		{
			if (type == _type_courant_ouvre_valve && index == _index_courant_ouvre_valve) 
			{
			   if (type == "a" && _nb == 0 && _na < _n && _wa > 0 && (_wb == 0 || _t == 1)) 
			      {
					_na += 1;
					_wa -= 1;
				    if (_index_courant_ouvre_valve < Confiturerie.N) 
				        {
				     	  _index_courant_ouvre_valve +=1; 
				    	}
				    else 
				    	{
   		
				    	  _index_courant_ferme_valve = 1;
				    	  _type_courant_ferme_valve = "a";
				
				    	}
 				    return true;
				  }
				
		
		    }
		
	
			if (type == _type_courant_ouvre_valve && index == _index_courant_ouvre_valve) 
			{
			    if (type == "b" && _na == 0 && _nb < _n && _wb > 0 && (_wa == 0 || _t == 2)) 
			    {
				   _nb += 1;
				   _wb -= 1;
				   if (_index_courant_ouvre_valve < Confiturerie.N) 
			         {
			     	   _index_courant_ouvre_valve +=1; 
			    	 }
			       else 
			    	 {
			
			    	   _index_courant_ouvre_valve = 1;
			    	   _index_courant_ferme_valve = 1;
			    	   _type_courant_ferme_valve = "b";
			    	 }
			       return true;
			    }
			    else
			    {
			       return false;
			    }
		    
			}
		
		return false;
		
		}	
	
}
	
	public boolean fermeValve(int index, String type) 
{
		
		synchronized(lock) 
		{
			if (type == _type_courant_ferme_valve && index == _index_courant_ferme_valve) 
			{
				if (type == "a" && _na > 0) 
				{
		     		_na -= 1;
			    	_t = 2;
			    	if (_index_courant_ferme_valve < Confiturerie.N) 
			        {
			          _index_courant_ferme_valve +=1; 
			        }
			        else 
			    	{
			          _index_courant_ferme_valve = 1 ;
			          _index_courant_ouvre_valve = 1;
				      _type_courant_ouvre_valve = "b";
			    	}
			    	return true;
				}
			    
			   	    
			}
						
			if (type == _type_courant_ferme_valve && index == _index_courant_ferme_valve) 
			{
				if (type == "b" && _nb > 0) 
			    {
				  _nb -= 1;
				   _t = 1;
				   if (_index_courant_ferme_valve < Confiturerie.N) 
			        {
			          _index_courant_ferme_valve +=1; 
			        }
			        else 
			    	{
			          _index_courant_ferme_valve = 1 ;
			          _index_courant_ouvre_valve += 1;
			    	  _type_courant_ouvre_valve = "a";
			    	}
				    return true;
			    }
				else
				{
				    return false;
				}
			}	
		return false;
		}
}	
}

