import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<Integer> dataList = new ArrayList<Integer>();
        for (int i = 0; i < 12; ++i)
        {
            dataList.add(i);
        }
        System.out.println("总数据集：" + dataList);
        long start = System.currentTimeMillis();
        WorkThread[] workThreadArray = new WorkThread[4];
        for (int i = 0; i < workThreadArray.length; ++i)
        {
            workThreadArray[i] = new WorkThread("线程" + i, dataList.subList(i * 3, (i + 1) * 3));
            workThreadArray[i].start();
        }
        for (WorkThread aWorkThread : workThreadArray)
        {
            try
            {
                aWorkThread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("结果汇总：" + dataList);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    static class WorkThread extends Thread
    {
        private List<Integer> workDataList;

        WorkThread(String name, List<Integer> workDataList)
        {
            super(name);
            this.workDataList = workDataList;
        }

        @Override
        public void run()
        {
            System.out.println(getName() + "开始处理" + workDataList);
            for (int i = 0; i < workDataList.size(); ++i)
            {
                workDataList.set(i, workDataList.get(i) + 1);
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println(getName() + "处理完毕" + workDataList);
        }

        public List<Integer> getResult()
        {
            return workDataList;
        }
    }
}