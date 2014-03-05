package com.fancl.iloyalty.asynctask;

import android.os.AsyncTask;

import com.fancl.iloyalty.asynctask.callback.GetPromotionListAsyncTaskCallback;
import com.fancl.iloyalty.exception.FanclException;
import com.fancl.iloyalty.factory.CustomServiceFactory;
import com.fancl.iloyalty.util.LogController;

public class GetPromotionListAsyncTask extends AsyncTask<String, Void, Object>{

private GetPromotionListAsyncTaskCallback getPromotionListAsyncTaskCallback;
	
	public GetPromotionListAsyncTask(GetPromotionListAsyncTaskCallback getPromotionListAsyncTaskCallback)
	{
		this.getPromotionListAsyncTaskCallback = getPromotionListAsyncTaskCallback;
	}
	
	@Override
	protected void onPreExecute () {
		super.onPreExecute();
		//process of thread before start(UI Thread)
	}
	
	@Override
	protected Object doInBackground(String... params) {
		//process of thread(background thread)
		
		LogController.log("ExampleAsyncTask doInBackground >>> " + params[0]);
		
		try
		{
			return CustomServiceFactory.getPromotionService().getPromotionListWithType(params[0]);
		}
		catch (FanclException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected void onPostExecute (Object results) {
		super.onPostExecute(results);
		//process of thread ended(UI Thread)
		
		if(getPromotionListAsyncTaskCallback != null)
		{
			getPromotionListAsyncTaskCallback.onPostExecuteCallback(results);
		}
	}
}
