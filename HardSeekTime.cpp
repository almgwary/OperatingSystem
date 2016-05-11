//============================================================================
// Name        : SeekTime
// Author      : Almgwary
// Date        : 2015
//============================================================================
#include <windows.h>
# include <bits\stdc++.h>
# include <conio.h>
# include <math.h>
using namespace std;
#define IDC_MAIN_BUTTON 101
LRESULT CALLBACK WindowProcedure (HWND, UINT, WPARAM, LPARAM);




vector<int> C_LOOKNodes , C_SCANNodes,SCANNodes , FCFSNodes ;


int const start = 0 , eNd = 4999 ;
//revers vector
vector <int> revers (vector<int> nodes)
{
    int size = nodes.size();
    size--;
    for(int i = 0 ; i < size/2-1 ; ++i)
    {
        swap(nodes[i],nodes[size-i]);
    }
    return nodes ;
}

vector <int> FCFS (vector <int> nodes , int head , int direction )
{

    vector <int>sequnce = nodes;
    sequnce.insert(sequnce.begin(),head);
    return sequnce;
}

vector <int> SSTF (vector <int> nodes , int head , int direction )
{

    vector <int> sequnce ;
    nodes.push_back(head);
    sort(nodes.begin(),nodes.end());
    int size = nodes.size();
    for(int i = 0 ; i < size ; ++i)
    {

        if(nodes[i]== head)
        {
            sequnce.push_back(head);
            while(size>1)
            {
                if(i==0)
                {
                    sequnce.push_back(nodes[1]);
                    nodes.erase(nodes.begin());
                }
                else if(i==size-1)
                {
                    sequnce.push_back(nodes[size-2]);
                    nodes.erase(nodes.end());
                    --i;
                }
                else if (nodes[i]-nodes[i-1]> nodes[i+1]-nodes[i])
                {
                    sequnce.push_back(nodes[i+1]);
                    nodes.erase(nodes.begin()+i);
                }
                else
                {
                    sequnce.push_back(nodes[i-1]);
                    nodes.erase(nodes.begin()+i);
                    --i;
                }
                --size;
            }
        }
    }

    return sequnce;
}

vector <int> SCAN (vector <int> nodes , int head , int direction )
{

    vector <int> sequnce ;
    nodes.push_back(head);
    nodes.push_back(start);
    nodes.push_back(eNd) ;
    sort(nodes.begin(),nodes.end());

    int size = nodes.size();
    --size;

    if(direction<0)
    {
        nodes = revers(nodes);
    }

    for(int i = 0 ; i < size ; ++i)
    {
        if(nodes[i]== head)
        {

            while(size>-1)
            {
                sequnce.push_back(nodes[i]);
                nodes.erase(nodes.begin()+i);
                if(i == size)
                {
                    --i;
                }
                --size;
            }

        }
    }

    return sequnce;
}

vector <int> C_SCAN (vector <int> nodes , int head , int direction )
{

    vector <int> sequnce ;
    nodes.push_back(head);
    nodes.push_back(start);
    nodes.push_back(eNd) ;
    sort(nodes.begin(),nodes.end());

    int size = nodes.size();
    --size;

    if(direction<0)
    {
        nodes = revers(nodes);
    }

    for(int i = 0 ; i < size ; ++i)
    {
        if(nodes[i]== head)
        {

            while(size>-1)
            {
                sequnce.push_back(nodes[i]);
                nodes.erase(nodes.begin()+i);
                if(i == size)
                {
                    i=0;
                }
                --size;
            }

        }
    }

    return sequnce;
}

vector <int> C_LOOK (vector <int> nodes , int head , int direction )
{

    vector <int> sequnce ;
    nodes.push_back(head);
    sort(nodes.begin(),nodes.end());

    int size = nodes.size();
    --size;

    if(direction<0)
    {
        nodes = revers(nodes);
    }

    for(int i = 0 ; i < size ; ++i)
    {
        if(nodes[i]== head)
        {

            while(size>-1)
            {
                sequnce.push_back(nodes[i]);
                nodes.erase(nodes.begin()+i);
                if(i == size)
                {
                    i=0;
                }
                --size;
            }

        }
    }

    return sequnce;
}


void swap(int &x1, int &y1,int &x2, int &y2)
{
    int tmp=x1;
    x1=x2;
    x2=tmp;
    int tmpe=y1;
    y1=y2;
    y2=tmpe;
}


void parametric_line(HDC hdc, int xs, int ys, int xe, int ye,COLORREF color)
{
    int dx=xe-xs;
    int dy=ye-ys;

    if(abs(dy)<=abs(dx))
    {
        if(xs>xe) swap(xs,ys,xe,ye);
        double x=xs,y=ys;
        SetPixel(hdc,x,y,color);
        int dx=xe-xs;
        int dy=ye-ys;
        double m=(double)dy/dx;
        while(x<xe)
        {
            x=x+1;
            y=y+m;
            SetPixel(hdc,(int)x,(int)y,color);
        }
    }
    else
    {
        if(ys>ye) swap(xs,ys,xe,ye);
        double x=xs,y=ys;
        SetPixel(hdc,(int)x,(int)y,color);
        int dx=xe-xs;
        int dy=ye-ys;
        double m=(double)dy/dx;
        while((int)y<ye)
        {
            y=y+1;
            x+=(1/m);
            SetPixel(hdc,(int)x ,(int)y,color);
        }
    }
}

void Draw8Points(HDC hdc,int xCenter,int yCenter, int a, int b,COLORREF color)
{

    SetPixel(hdc, xCenter+a, yCenter+b, color);
    SetPixel(hdc, xCenter+a, yCenter-b, color);
     parametric_line( hdc,xCenter+a, yCenter+b, xCenter+a, yCenter-b, color) ;


    SetPixel(hdc, xCenter-a, yCenter-b, color);
    SetPixel(hdc, xCenter-a, yCenter+b, color);
    parametric_line( hdc,xCenter-a, yCenter-b, xCenter-a, yCenter+b, color) ;

    SetPixel(hdc, xCenter+b, yCenter+a, color);
    SetPixel(hdc, xCenter+b, yCenter-a, color);
     parametric_line( hdc,xCenter+b, yCenter+a, xCenter+b, yCenter-a, color) ;

    SetPixel(hdc, xCenter-b, yCenter+a, color);
    SetPixel(hdc, xCenter-b, yCenter-a, color);
    parametric_line( hdc,xCenter-b, yCenter+a, xCenter-b, yCenter-a, color) ;


}

void drowCircleCartisan(HDC hdc,int xc,int yc, int x, int y,COLORREF color)
{
    int R=sqrt(pow(x-xc,2)+pow(y-yc,2));

    int xCenter=0,yCenter=R;
    int R2=R*R;
    Draw8Points(hdc,xc,yc,xCenter,yCenter,color);
    while(xCenter<yCenter)
    {
        xCenter++;
        yCenter=round(sqrt((double)(R2-xCenter*xCenter)));
        Draw8Points(hdc,xc,yc,xCenter,yCenter,color);
    }

}


// nodes vector of  positions sequnce nodes of any algorithm
void mainCaller (  HDC hdc,vector <int> nodes,COLORREF color)
{
   int h=0;
   for(int i = 1  ; i< nodes.size() ; ++i){
      //drow small point on node
     drowCircleCartisan( hdc,(nodes[i-1]) / 5,h , (nodes[i-1])/ 5 +5, h+5,RGB(0,0 ,0));
      //drow line betwen 2 nodes
     parametric_line   (hdc,(nodes[i-1]) / 5, h , (nodes[i])  / 5 , (h+30),color);
     h+=30;
   }

}

int counter1=0;

int algo=0;
int xP1=0,xP2=0,yP1=0,yP2=0,yP3=0,xP3=0;



LRESULT CALLBACK WindowProcedure (HWND hwnd, UINT message, WPARAM wParam, LPARAM lParam)
{


    PAINTSTRUCT P;
    switch (message)                  /* handle the messages */
    {
    case WM_CREATE:
    {
        HMENU hMenubar = CreateMenu();
        SetMenu(hwnd, hMenubar);
        break;
    }


    case WM_PAINT:
    {
        if (counter1==2)
        {

            BeginPaint(hwnd,&P);
            //mainCaller( P.hdc,FCFSNodes ,RGB(0,0,255));
            //mainCaller( P.hdc,SCANNodes ,RGB(0,255,0));
            mainCaller(P.hdc,C_SCANNodes ,RGB(588,0,0));
            //mainCaller( P.hdc,C_LOOKNodes ,RGB(255,588,0));
            counter1=7;
            EndPaint(hwnd,&P);
            break;
        }
        case WM_LBUTTONDOWN:
        {
                if(counter1!=7)counter1 = 2;
                InvalidateRect(hwnd,NULL,false);

            break;
        }



        case WM_COMMAND:
            break;



        case WM_DESTROY:
            PostQuitMessage (0);
            break;
        default:
            return DefWindowProc (hwnd, message, wParam, lParam);
        }
        return 0;
    }
}







/*  Make the class name into a global variable  */
char szClassName[ ] = "WindowsApp";

int WINAPI WinMain (HINSTANCE hThisInstance,
                    HINSTANCE hPrevInstance,
                    LPSTR lpszArgument,

                    int nFunsterStil)

{

    vector <int> nodes ;
            int x ,head ,direction;
            cout<<"EnterNodes"<<endl;
            cin>>x;
            while(x>-1)
            {
                nodes.push_back(x);
                cin>>x;
            }
            cout<<"EnterHead"<<endl;
            cin>>head;
            cout<<"EnterDirection"<<endl;
            cin>>direction;

            cout<<" FCFS "<<endl;
              FCFSNodes = FCFS (nodes , head , direction) ;

            int diffrence = 0;
            cout<<FCFSNodes[0]<<" ";
            for(int i = 1 ; i < FCFSNodes.size() ; ++i )
            {
                diffrence+=abs(FCFSNodes[i-1]-FCFSNodes[i]);
                cout<<FCFSNodes[i]<<" ";
            }
            cout<<" Total "<<diffrence<<endl;
            diffrence=0;
            cout<<"\n SSTF "<<endl;



            vector<int> SSTFNodes = SSTF (nodes , head , direction) ;
              cout<<SSTFNodes[0]<<" ";

            for(int i = 1 ; i < SSTFNodes.size() ; ++i )
            {
                diffrence+=abs(SSTFNodes[i-1]-SSTFNodes[i]);
                cout<<SSTFNodes[i]<<" ";
            }
            cout<<" Total "<<diffrence<<endl;
            diffrence=0;

            cout<<"\n SCAN "<<endl;
             SCANNodes = SCAN (nodes , head , direction) ;
                SCANNodes.pop_back();
            cout<<SCANNodes[0]<<" ";
            for(int i = 1 ; i < SCANNodes.size() ; ++i )
            {
                diffrence+=abs(SCANNodes[i-1]-SCANNodes[i]);
                cout<<SCANNodes[i]<<" ";
            }
            cout<<" Total "<<diffrence<<endl;
            diffrence=0;
            cout<<"\n C_SCAN "<<endl;
            C_SCANNodes = C_SCAN (nodes , head , direction) ;
            cout<<C_SCANNodes[0]<<" ";
            for(int i = 1 ; i < C_SCANNodes.size() ; ++i )
            {
                diffrence+=abs(C_SCANNodes[i-1]-C_SCANNodes[i]);
                cout<<C_SCANNodes[i]<<" ";
            }

                cout<<" Total "<<diffrence<<endl;
            diffrence=0;

            cout<<"\n C_LOOK "<<endl;
              C_LOOKNodes = C_LOOK (nodes , head , direction) ;

            cout<<C_LOOKNodes[0]<<" ";
            for(int i = 1 ; i < C_LOOKNodes.size() ; ++i )
            {
                diffrence+=abs(C_LOOKNodes[i-1]-C_LOOKNodes[i]);
                cout<<C_LOOKNodes[i]<<" ";
            }
            cout<<" Total "<<diffrence<<endl;
            diffrence=0;
            cout<<endl;




    HWND hwnd;               /* This is the handle for our window */
    MSG messages;            /* Here messages to the application are saved */
    WNDCLASSEX wincl;        /* Data structure for the windowclass */

    /* The Window structure */
    wincl.hInstance = hThisInstance;
    wincl.lpszClassName = szClassName;
    wincl.lpfnWndProc = WindowProcedure;      /* This function is called by windows */
    wincl.style = CS_DBLCLKS;                 /* Catch double-clicks */
    wincl.cbSize = sizeof (WNDCLASSEX);

    /* Use default icon and mouse-pointer */
    wincl.hIcon = LoadIcon (NULL, IDI_APPLICATION);
    wincl.hIconSm = LoadIcon (NULL, IDI_APPLICATION);
    wincl.hCursor = LoadCursor (NULL, IDC_ARROW);
    wincl.lpszMenuName = NULL;                 /* No menu */
    wincl.cbClsExtra = 0;                      /* No extra bytes after the window class */
    wincl.cbWndExtra = 0;                      /* structure or the window instance */
    /* Use Windows's default color as the background of the window */
    wincl.hbrBackground = (HBRUSH) RGB(0,0,0);

    /* Register the window class, and if it fails quit the program */
    if (!RegisterClassEx (&wincl))
        return 0;

    /* The class is registered, let's create the program*/
    hwnd = CreateWindowEx (
               0,                   /* Extended possibilites for variation */
               szClassName,         /* Classname */
               "Windows App",       /* Title Text */
               WS_OVERLAPPEDWINDOW, /* default window */
               CW_USEDEFAULT,       /* Windows decides the position */
               CW_USEDEFAULT,       /* where the window ends up on the screen */
               1000,                 /* The programs width */
               500,                 /* and height in pixels */
               HWND_DESKTOP,        /* The window is a child-window to desktop */
               NULL,                /* No menu */
               hThisInstance,       /* Program Instance handler */
               NULL                 /* No Window Creation data */
           );

    /* Make the window visible on the screen */
    ShowWindow (hwnd, nFunsterStil);

    /* Run the message loop. It will run until GetMessage() returns 0 */
    while (GetMessage (&messages, NULL, 0, 0))
    {
        /* Translate virtual-key messages into character messages */
        TranslateMessage(&messages);
        /* Send message to WindowProcedure */
        DispatchMessage(&messages);
    }

    /* The program return-value is 0 - The value that PostQuitMessage() gave */
    return messages.wParam;
}



    //98  183  37  122  14 124 65  67 -1 53 1
    //86 1470 913 1774 948 1509 1022 1750 130 -1 143 -5
    //86 1470 913 1774 948 1509 1022 1750 130 -1 143 1
    //53 89 183 37 122 14 124 65 67



/*


      vector <int> nodes ;
            int x ,head ,direction;
            cout<<"EnterNodes"<<endl;
            cin>>x;
            while(x>-1)
            {
                nodes.push_back(x);
                cin>>x;
            }
            cout<<"EnterHead"<<endl;
            cin>>head;
            cout<<"EnterDirection"<<endl;
            cin>>direction;

            cout<<" FCFS "<<endl;
            vector<int> FCFSNodes = FCFS (nodes , head , direction) ;
            for(int i = 0 ; i < FCFSNodes.size() ; ++i )
            {
                cout<<FCFSNodes[i]<<" ";
            }
            cout<<"\n SSTF "<<endl;
            vector<int> SSTFNodes = SSTF (nodes , head , direction) ;
            for(int i = 0 ; i < SSTFNodes.size() ; ++i )
            {
                cout<<SSTFNodes[i]<<" ";
            }

            cout<<"\n SCAN "<<endl;
            vector<int> SCANNodes = SCAN (nodes , head , direction) ;
            for(int i = 0 ; i < SCANNodes.size() ; ++i )
            {
                cout<<SCANNodes[i]<<" ";
            }

            cout<<"\n C_SCAN "<<endl;
            vector<int> C_SCANNodes = C_SCAN (nodes , head , direction) ;
            for(int i = 0 ; i < C_SCANNodes.size() ; ++i )
            {
                cout<<C_SCANNodes[i]<<" ";
            }



            cout<<"\n C_LOOK "<<endl;
            vector<int> C_LOOKNodes = C_LOOK (nodes , head , direction) ;
            for(int i = 0 ; i < C_LOOKNodes.size() ; ++i )
            {
                cout<<C_LOOKNodes[i]<<" ";
            }



*/
