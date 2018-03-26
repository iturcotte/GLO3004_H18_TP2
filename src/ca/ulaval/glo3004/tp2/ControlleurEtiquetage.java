package ca.ulaval.glo3004.tp2;


public class ControlleurEtiquetage {
	
	public Object lock2 = new Object();
	
	private int _n;
	
	
	private int _na = 0;
	private int _nb = 0;
	private int _wa = 0;
	private int _wb = 0;
	
	private int _t = 1;
	
	private String _type_initial = "a";
	private String _type_courant_commence_etiquetage = _type_initial;
	private String _type_courant_termine_etiquetage = "a";
	private int _index_initial = 1;

	private int _index_courant_commence_etiquetage = _index_initial;
	private int _index_courant_termine_etiquetage = 1;
	
	public ControlleurEtiquetage(int n) {
		_n = n;
		
	}
	
	
	public boolean requeteEtiquetage(int index, String type) 
	{
		
		synchronized(lock2) 
		{
			
			if (type == "a" && _wa < _n) {
				_wa += 1;
				return true;
			}
			if (type == "b" && _wb < _n) {
				_wb += 1;
				return true;
			}
			
		
		}
		return false;
	}
	
	public boolean CommenceEtiquetage(int index, String type) 
{
		
		synchronized(lock2) 
	{
			if (type == _type_courant_commence_etiquetage && index == _index_courant_commence_etiquetage) 
			{	
				if (type == "a" && _nb == 0 && _na < _n && _wa > 0 && (_wb == 0 || _t == 1)) 
				{
					_na += 1;
					_wa -= 1;
					 if (_index_courant_commence_etiquetage < Confiturerie.N) 
				        {
				     	  _index_courant_commence_etiquetage +=1; 
				    	}
				    else 
				    	{
		
				    	  _index_courant_termine_etiquetage = 1;
				    	  _type_courant_termine_etiquetage = "a";
				
				    	}
					 return true;
				}	
					
			}
			
			if (type == _type_courant_commence_etiquetage && index == _index_courant_commence_etiquetage) 
			{		
				if (type == "b" && _na == 0 && _nb < _n && _wb > 0 && (_wa == 0 || _t == 2)) 
				{
					_nb += 1;
					_wb -= 1;
					if (_index_courant_commence_etiquetage < Confiturerie.N) 
			         {
			     	   _index_courant_commence_etiquetage +=1; 
			    	 }
			        else 
			    	 {
			
			    	   _index_courant_commence_etiquetage = 1;
			    	   _index_courant_termine_etiquetage = 1;
			    	   _type_courant_termine_etiquetage = "b";
			    	 }
					
					return true;
				}
				else
			    {
			       return false;
			    }
		    }


	}
		return false;
}
	
	public boolean TermineEtiquetage(int index, String type) 
{
		
    synchronized(lock2) 
	{
			
    	if (type == _type_courant_termine_etiquetage && index == _index_courant_termine_etiquetage) 
		{
    		if (type == "a" && _na > 0) 
    		{
		
				_na -= 1;
				_t = 2;
				if (_index_courant_termine_etiquetage < Confiturerie.N) 
		        {
		          _index_courant_termine_etiquetage +=1; 
		        }
		        else 
		    	{
		          _index_courant_termine_etiquetage = 1 ;
		          _index_courant_commence_etiquetage = 1;
			      _type_courant_commence_etiquetage = "b";
		    	}
				return true;
		
    		}
		}
    		
    	if (type == _type_courant_termine_etiquetage && index == _index_courant_termine_etiquetage) 
		{	
    		if (type == "b" && _nb > 0) {
				_nb -= 1;
				_t = 1;
				if (_index_courant_termine_etiquetage < Confiturerie.N) 
		        {
		          _index_courant_termine_etiquetage +=1; 
		        }
		        else 
		    	{
		          _index_courant_termine_etiquetage = 1 ;
		          _index_courant_commence_etiquetage += 1;
		    	  _type_courant_commence_etiquetage = "a";
		    	}
				return true;
			}
    		
		  }
    	
		}
    return false;
	}
	
}


