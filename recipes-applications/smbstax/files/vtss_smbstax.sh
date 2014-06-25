#! /bin/sh

case "$1" in
      start)
            echo -n "Starting vtss_smbstax: "
            start-stop-daemon -S -b -n vtss_smbstax -a /usr/bin/vtss_smbstax
            echo "done"
            ;;
      stop)
            echo -n "Stopping vtss_smbstax: "
            start-stop-daemon -K -n vtss_smbstax > /dev/null
            echo "done"
            ;;
      restart)
            echo -n "Restarting vtss_smbstax: "
            start-stop-daemon -K -n vtss_smbstax > /dev/null
            start-stop-daemon -S -b -n vtss_smbstax -a /usr/bin/vtss_smbstax
            echo "done"
            ;;
      *)
            echo "Usage: vtss_smbstax { start | stop | restart }" >&2
            exit 1
            ;;
esac
exit 0 
